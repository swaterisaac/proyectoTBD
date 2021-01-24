package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Task;
import grupo2.proyectoTBD.models.TaskUpdate;
import grupo2.proyectoTBD.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import grupo2.proyectoTBD.repositories.TaskRepository;
import grupo2.proyectoTBD.repositories.TaskUpdateRepository;

import java.util.List;

@RestController
@RequestMapping("/taskupdate")
public class TaskUpdateService {
    private final grupo2.proyectoTBD.repositories.TaskRepository TaskRepository;
    private final grupo2.proyectoTBD.repositories.TaskUpdateRepository TaskUpdateRepository;
    private final Gson gson;

    TaskUpdateService(TaskUpdateRepository TaskUpdateRepository, TaskRepository TaskRepository){
        this.TaskUpdateRepository = TaskUpdateRepository;
        this.TaskRepository = TaskRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    String getTaskUp() {
        List<TaskUpdate> tu = TaskUpdateRepository.getTasks();
        return gson.toJson(tu);
    }


    @PostMapping({"","/"})
    ResponseEntity<String> newTaskUp(@RequestBody String request){
        TaskUpdate tu = gson.fromJson(request,TaskUpdate.class);
        if(tu != null){
            if(tu.getId_task() != null) {
                if (TaskRepository.getTask(tu.getId_task()) == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                if(tu.getDescription() != null){
                    tu = TaskUpdateRepository.newTaskUpdate(tu);
                    return new ResponseEntity<>(
                            gson.toJson(tu),
                            HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getTaskUpdate(@PathVariable Long id){
        TaskUpdate tu = TaskUpdateRepository.getTask(id);
        if(tu != null){
            return new ResponseEntity<>(
                    gson.toJson(tu),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editVolunteer(@PathVariable Long id,@RequestBody TaskUpdate request){
        TaskUpdate tu = TaskUpdateRepository.getTask(id);

        if(request != null && tu != null){
            if(request.getId_task() != null){
                if (TaskRepository.getTask(request.getId_task()) == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                tu.setId_task(request.getId_task());
            }
            if(request.getDescription() != null){
                tu.setDescription(request.getDescription());
            }
            tu = TaskUpdateRepository.editTaskUp(id,tu);
            return new ResponseEntity<>(
                    gson.toJson(tu),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTaskUpdate(@PathVariable Long id){
        if(TaskUpdateRepository.deleteTaskUp(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }
}
