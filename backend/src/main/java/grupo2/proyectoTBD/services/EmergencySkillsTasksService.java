package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency_Skills_Tasks;
import grupo2.proyectoTBD.repositories.EmergencySkillsTasksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EmergencySkillTask")
public class EmergencySkillsTasksService {

    private final EmergencySkillsTasksRepository EmergencySkillsTasksRepository;
    private final Gson gson;

    EmergencySkillsTasksService(EmergencySkillsTasksRepository EmergencySkillsTasksRepository){
        this.EmergencySkillsTasksRepository = EmergencySkillsTasksRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/")
    ResponseEntity<String> getEmeSkiTas() {
        List<Emergency_Skills_Tasks> emerskilltask = EmergencySkillsTasksRepository.getEmeSkiTasks();
        return new ResponseEntity<>(
                gson.toJson(emerskilltask),
                HttpStatus.OK);
    }


    @PostMapping("/")
    ResponseEntity<String> newEmeSkiTasks(@RequestBody String request){
        Emergency_Skills_Tasks emerskilltask = gson.fromJson(request,Emergency_Skills_Tasks.class);
        emerskilltask = EmergencySkillsTasksRepository.newEmeSkiTas(emerskilltask);
        if(emerskilltask != null){
            return new ResponseEntity<>(
                    gson.toJson(emerskilltask),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getEmeSkiTas(@PathVariable Long id){
        Emergency_Skills_Tasks emerskilltask = EmergencySkillsTasksRepository.getEmeSkiTas(id);
        if(emerskilltask != null){
            return new ResponseEntity<>(
                    gson.toJson(emerskilltask),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //UPDATE
    @PostMapping("/edit/{id}")
    ResponseEntity<String> updateEST(@RequestBody String ESTRequest, @PathVariable Long id) {
        Emergency_Skills_Tasks ESTRequested = gson.fromJson(ESTRequest, Emergency_Skills_Tasks.class);
        if (ESTRequested!=null){
            return new ResponseEntity<>(gson.toJson(EmergencySkillsTasksRepository.editEmeSkiTas(id,ESTRequested)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PutMapping("/{id}")
    ResponseEntity<String> editEmeSkiTas(@PathVariable Long id,@RequestBody String request){
        Emergency_Skills_Tasks emerskilltask = gson.fromJson(request,Emergency_Skills_Tasks.class);
        emerskilltask = EmergencySkillsTasksRepository.editEmeSkiTas(id,emerskilltask);
        if(emerskilltask != null){
            return new ResponseEntity<>(
                    gson.toJson(emerskilltask),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmeSkiTas(@PathVariable Long id){
        if(EmergencySkillsTasksRepository.deleteEmeSkiTas(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

}
