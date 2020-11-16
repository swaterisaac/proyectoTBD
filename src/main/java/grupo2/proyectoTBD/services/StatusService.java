package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Status;
import grupo2.proyectoTBD.repositories.StatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
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
    ResponseEntity<String> newStatus(@RequestBody String request){
        Status status = gson.fromJson(request,Status.class);
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
    ResponseEntity<String> editStatus(@PathVariable Long id,@RequestBody String request){
        Status status = gson.fromJson(request,Status.class);
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
