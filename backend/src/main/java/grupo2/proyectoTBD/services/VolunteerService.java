package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Task;
import grupo2.proyectoTBD.models.User;
import grupo2.proyectoTBD.models.Volunteer;
import grupo2.proyectoTBD.repositories.EmergencyRepository;
import grupo2.proyectoTBD.repositories.TaskRepository;
import grupo2.proyectoTBD.repositories.UserRepository;
import grupo2.proyectoTBD.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/volunteers")
public class VolunteerService {

    private final grupo2.proyectoTBD.repositories.VolunteerRepository VolunteerRepository;
    private final grupo2.proyectoTBD.repositories.UserRepository UserRepository;
    private final Gson gson;
    @Autowired
    private JavaMailSender mailSender;
    private final grupo2.proyectoTBD.repositories.EmergencyRepository EmergencyRepository;
    private final grupo2.proyectoTBD.repositories.TaskRepository TaskRepository;

    VolunteerService(VolunteerRepository VolunteerRepository, UserRepository UserRepository, EmergencyRepository EmergencyRepository, TaskRepository TaskRepository){
        this.VolunteerRepository = VolunteerRepository;
        this.UserRepository = UserRepository;
        this.EmergencyRepository = EmergencyRepository;
        this.TaskRepository = TaskRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/")
    ResponseEntity<String> getVolunteeres() {
        List<Volunteer> volunteer = VolunteerRepository.getVolunteeres();
        return new ResponseEntity<>(
                gson.toJson(volunteer),
                HttpStatus.OK);
    }


    @PostMapping("/")
    ResponseEntity<String> newVolunteer(@RequestBody String request){
        Volunteer volunteer = gson.fromJson(request,Volunteer.class);
        if(volunteer != null){
            if(volunteer.getId_user() != null) {
                if (UserRepository.getUser(volunteer.getId_user()) == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                volunteer = VolunteerRepository.newVolunteer(volunteer);
                return new ResponseEntity<>(
                        gson.toJson(volunteer),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getVolunteer(@PathVariable Long id){
        Volunteer volunteer = VolunteerRepository.getVolunteer(id);
        if(volunteer != null){
            return new ResponseEntity<>(
                    gson.toJson(volunteer),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editVolunteer(@PathVariable Long id,@RequestBody String request){
        Volunteer volunteer = gson.fromJson(request,Volunteer.class);
        if(volunteer != null){
            if(volunteer.getId_user() != null){
                if(UserRepository.getUser(volunteer.getId_user()) == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                volunteer = VolunteerRepository.editVolunteer(id,volunteer);
                return new ResponseEntity<>(
                        gson.toJson(volunteer),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteVolunteer(@PathVariable Long id){
        if(VolunteerRepository.deleteVolunteer(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

    @PostMapping("/email")
    ResponseEntity<String> sendMail(@RequestBody Map<String, Object> request){
        String str_volunteers = request.get("volunteers").toString();
        List<Long> volunteers_id= Arrays.stream(str_volunteers.substring(1,str_volunteers.length()-1).split(", ")).map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayList::new));

        Task task = TaskRepository.getTask(Long.parseLong(request.get("emergency_id").toString()));
        Emergency emergency = EmergencyRepository.getEmergency(task.getId_emergency());

        String subject = "TBD: Seleccionado en la tarea: "+task.getName();
        String message = "Hola %s, has sido seleccionado para la tarea "+task.getName()+", la cual consiste en "+task.getDescription()+" de la emergencia "+emergency.getName()+" - "+emergency.getDescription();


        for(Long id: volunteers_id){
            Long volunteer_userid = VolunteerRepository.getVolunteer(id).getId_user();
            User user = UserRepository.getUser(volunteer_userid);
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("no-contestar@tbd.cl");
            email.setTo(user.getEmail());
            email.setSubject(subject);
            email.setText(String.format(message,user.getNombre()));

            mailSender.send(email);
        }

        return new ResponseEntity<>(
                HttpStatus.OK);
    }


}
