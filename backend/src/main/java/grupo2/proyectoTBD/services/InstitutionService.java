package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Institution;
import grupo2.proyectoTBD.repositories.InstitutionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionService {
    private final InstitutionRepository InstitutionRepository;
    private final Gson gson;

    InstitutionService(InstitutionRepository InstitutionRepository){

        this.InstitutionRepository = InstitutionRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getInstitution(@PathVariable Long id){
        Institution inst = InstitutionRepository.getInstitution(id);

        if(inst != null){
            return new ResponseEntity<>(
                    gson.toJson(inst),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"","/"})
    ResponseEntity<String> getInstitutions() {
        List<Institution> inst = InstitutionRepository.getInstitutions();
        return new ResponseEntity<>(
                gson.toJson(inst),
                HttpStatus.OK);
    }

    @PostMapping({"","/"})
    ResponseEntity<String> newInstitution(@RequestBody String request){
        Institution inst = gson.fromJson(request,Institution.class);
        if(inst != null && inst.getName() != null){
            inst = InstitutionRepository.newInstitution(inst);
            return new ResponseEntity<>(
                    gson.toJson(inst),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editInstitution(@RequestBody Institution institution, @PathVariable Long id){
        Institution inst = InstitutionRepository.getInstitution(id);

        if (institution.getName() != null){
            inst.setName(institution.getName());
        }

        inst = InstitutionRepository.editInstitution(inst, id);

        if(inst != null){
            return new ResponseEntity<>(
                    gson.toJson(inst),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteInstitution(@PathVariable Long id){
        if(InstitutionRepository.deleteInstitution(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }
}
