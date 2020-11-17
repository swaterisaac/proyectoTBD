package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Task;
import grupo2.proyectoTBD.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

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
    public String getTask(@PathVariable Long id){
        Task task = TaskRepository.getTask(id);
        return gson.toJson(task);
    }

    @GetMapping("/all")
    public String getTasks(){
        List<Task> tasks = TaskRepository.getTasks();
        return gson.toJson(tasks);
    }

    //CREATE
    @PostMapping("/new")
    public String newTask(@RequestBody String taskRequest){
        Task task = gson.fromJson(taskRequest, Task.class);
        return gson.toJson(TaskRepository.newTask(task));
    }


    //UPDATE
    @PostMapping("/edit/{id}")
    public String updateTask(@RequestBody String taskRequest, @PathVariable Long id) {
        Task taskRequested = gson.fromJson(taskRequest, Task.class);
        Task currentTask = TaskRepository.getTask(id);
        currentTask.setName(taskRequested.getName());
        currentTask.setDescription(taskRequested.getDescription());
        currentTask.setVolunteer_required(taskRequested.getVolunteer_required());
        currentTask.setVolunteer_registered(taskRequested.getVolunteer_registered());
        currentTask.setStart_date(taskRequested.getStart_date());
        currentTask.setFinal_date(taskRequested.getFinal_date());
        currentTask.setCreated_at(taskRequested.getCreated_at());
        currentTask.setDeleted(taskRequested.getDeleted());
        return gson.toJson(TaskRepository.editTask(currentTask));
    }


}
