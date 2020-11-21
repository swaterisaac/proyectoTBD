package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Task;
import grupo2.proyectoTBD.repositories.TaskRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskService {

    private final TaskRepository TaskRepository;
    private final Gson gson;

    TaskService(TaskRepository TaskRepository){ //ERROR??? ayuda
        this.TaskRepository = TaskRepository;
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
            return new ResponseEntity<>(gson.toJson(TaskRepository.newTask(task)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //UPDATE
    @PutMapping("/{id}")
    ResponseEntity<String> updateTask(@Valid @RequestBody Task task , @PathVariable Long id) {
        if (task!=null){
            return new ResponseEntity<>(gson.toJson(TaskRepository.editTask(task, id)), HttpStatus.OK);
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
