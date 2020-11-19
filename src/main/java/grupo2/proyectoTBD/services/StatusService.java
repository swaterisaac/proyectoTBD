package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Status;
import grupo2.proyectoTBD.repositories.StatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/status")
@Validated
public class StatusService {

    private final StatusRepository StatusRepository;
    private final Gson gson;

    StatusService(StatusRepository StatusRepository){
        this.StatusRepository = StatusRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/")
    ResponseEntity<String> getStatuses() {
        List<Status> status = StatusRepository.getStatuses();
        return new ResponseEntity<>(
                gson.toJson(status),
                HttpStatus.OK);
    }


    @PostMapping("/")
    ResponseEntity<String> newStatus(@Valid @RequestBody Status status){

        status = StatusRepository.newStatus(status);
        if(status != null){
            return new ResponseEntity<>(
                    gson.toJson(status),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getStatus(@PathVariable Long id){
        Status status = StatusRepository.getStatus(id);
        if(status != null){
            return new ResponseEntity<>(
                    gson.toJson(status),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editStatus(@PathVariable Long id,@RequestBody Status status_r){
        Status status = StatusRepository.getStatus(id);
        if(status_r.getDescription() != null){
            status.setDescription(status_r.getDescription());
        }


        status = StatusRepository.editStatus(id,status);
        if(status != null){
            return new ResponseEntity<>(
                    gson.toJson(status),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteStatus(@PathVariable Long id){
        if(StatusRepository.deleteStatus(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

}
