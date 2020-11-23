package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Skill;
import grupo2.proyectoTBD.models.Task;
import grupo2.proyectoTBD.repositories.StatusRepository;
import grupo2.proyectoTBD.repositories.TaskRepository;
import grupo2.proyectoTBD.repositories.EmergencyRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class    TaskService {

    private final TaskRepository TaskRepository;
    private final EmergencyRepository EmergencyRepository;
    private final StatusRepository StatusRepository;
    private final Gson gson;

    TaskService(TaskRepository TaskRepository, StatusRepository StatusRepository, EmergencyRepository EmergencyRepository){
        this.TaskRepository = TaskRepository;
        this.StatusRepository = StatusRepository;
        this.EmergencyRepository = EmergencyRepository;
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

    @GetMapping("/")
    ResponseEntity<String> getTasks(){
        List<Task> tasks = TaskRepository.getTasks();
        if (tasks!=null){ //--->> Entrega null o una lista vacía????
            return new ResponseEntity<>(gson.toJson(tasks), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //CREATE
    @PostMapping("/")
    ResponseEntity<String> newTask(@Valid @RequestBody Task task){
        if (task!=null) {
            if (EmergencyRepository.getEmergency(task.getId_emergency()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (StatusRepository.getStatus(task.getId_status()) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if(task.getName() != null && task.getDescription() != null && task.getVolunteer_required() != null && task.getVolunteer_registered() != null && task.getStart_date() != null && task.getFinal_date() != null){
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

}
