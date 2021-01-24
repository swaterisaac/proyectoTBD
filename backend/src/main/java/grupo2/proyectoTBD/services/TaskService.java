package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.*;
import grupo2.proyectoTBD.repositories.*;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class    TaskService {

    private final TaskRepository TaskRepository;
    private final EmergencyRepository EmergencyRepository;
    private final StatusRepository StatusRepository;
    private final RankingRepository RankingRepository;
    private final VolunteerRepository VolunteerRepository;
    private final UserRepository UserRepository;
    private final Gson gson;

    TaskService(TaskRepository TaskRepository, StatusRepository StatusRepository, EmergencyRepository EmergencyRepository, grupo2.proyectoTBD.repositories.RankingRepository rankingRepository, grupo2.proyectoTBD.repositories.VolunteerRepository volunteerRepository, grupo2.proyectoTBD.repositories.UserRepository userRepository){
        this.TaskRepository = TaskRepository;
        this.StatusRepository = StatusRepository;
        this.EmergencyRepository = EmergencyRepository;
        this.RankingRepository = rankingRepository;
        this.VolunteerRepository = volunteerRepository;
        this.UserRepository = userRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getTask(@PathVariable Long id){
        Task task = TaskRepository.getTask(id);
        if (task!=null){
            return new ResponseEntity<>(gson.toJson(task), HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"","/"})
    ResponseEntity<String> getTasks(){
        List<Task> tasks = TaskRepository.getTasks();
        if (tasks!=null){ //--->> Entrega null o una lista vacía????
            return new ResponseEntity<>(gson.toJson(tasks), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //CREATE
    @PostMapping({"","/"})
    ResponseEntity<String> newTask(@Valid @RequestBody Task task){
        if (task!=null) {
            if (EmergencyRepository.getEmergency(task.getId_emergency()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (StatusRepository.getStatus(task.getId_status()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if(task.getName() != null && task.getDescription() != null && task.getVolunteer_required() != null && task.getVolunteer_registered() != null && task.getStart_date() != null && task.getFinal_date() != null && task.getLongitude() != null && task.getLatitude() != null){
                task = TaskRepository.newTask(task);
                return new ResponseEntity<>(
                        gson.toJson(task),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //UPDATE
    @PutMapping("/{id}")
    ResponseEntity<String> editTask(@PathVariable Long id,@RequestBody Task request){
        Task task = TaskRepository.getTask(id);

        if(request != null && task != null){
            if(request.getName() != null){
                task.setName(request.getName());
            }
            if(request.getDescription() != null){
                task.setDescription(request.getDescription());
            }
            if(request.getVolunteer_required() != null){
                task.setVolunteer_required(request.getVolunteer_required());
            }
            if(request.getVolunteer_registered() != null){
                task.setVolunteer_registered(request.getVolunteer_registered());
            }
            if(request.getStart_date() != null){
                task.setStart_date(request.getStart_date());
            }
            if(request.getFinal_date() != null){
                task.setFinal_date(request.getFinal_date());
            }
            if(request.getLongitude() != null){
                task.setLongitude(request.getLongitude());
            }
            if(request.getLatitude() != null){
                task.setLongitude(request.getLatitude());
            }
            if(request.getId_emergency() != null){
                if (EmergencyRepository.getEmergency(request.getId_emergency()) == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                task.setId_emergency(request.getId_emergency());
            }
            if(request.getId_status() != null){
                if (StatusRepository.getStatus(request.getId_status()) == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                task.setId_status(request.getId_status());
            }

            task = TaskRepository.editTask(task,id);
            return new ResponseEntity<>(
                    gson.toJson(task),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTask(@PathVariable Long id) {
        if(TaskRepository.deleteTask(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/emergencies/{id_emergency}")
    ResponseEntity<String> getEmergenciesTask(@PathVariable Long id_emergency){
        Emergency emergency = EmergencyRepository.getEmergency(id_emergency);
        System.out.println(emergency);
        //System.out.println(emergency.getTasks());
        if(!EmergencyRepository.getEmergency(id_emergency).equals(null)){
            List<Task> tasks = TaskRepository.getEmergencyTasks(id_emergency);
            return new ResponseEntity<>(
                    gson.toJson(tasks),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/{id}/volunteers")
    ResponseEntity<String> getTaskVolunteers(@PathVariable Long id){
        Task task = TaskRepository.getTask(id);
        if(task != null){
            List<Ranking> rankings = RankingRepository.getRankingsByTask(task.getId());
            List<JSONObject> response = new ArrayList<>();
            for (Ranking ranking: rankings) {
                Volunteer volunteer = VolunteerRepository.getVolunteer(ranking.getId_volunteer());
                User user = UserRepository.getUser(volunteer.getId_user());
                JSONObject obj = new JSONObject();
                obj.put("id",volunteer.getId());
                obj.put("name",user.getNombre());
                obj.put("score",ranking.getScore());
                response.add(obj);
            }

            return new ResponseEntity<>(
                    gson.toJson(response),
                    HttpStatus.OK
            );

        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );

    }

}

