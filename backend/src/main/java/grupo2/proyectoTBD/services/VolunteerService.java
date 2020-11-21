package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Volunteer;
import grupo2.proyectoTBD.repositories.UserRepository;
import grupo2.proyectoTBD.repositories.VolunteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerService {

    private final grupo2.proyectoTBD.repositories.VolunteerRepository VolunteerRepository;
    private final grupo2.proyectoTBD.repositories.UserRepository UserRepository;
    private final Gson gson;

    VolunteerService(VolunteerRepository VolunteerRepository, UserRepository UserRepository){
        this.VolunteerRepository = VolunteerRepository;
        this.UserRepository = UserRepository;
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
}
