package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency_Skill;
import grupo2.proyectoTBD.repositories.Emergency_SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency-skill")
public class Emergency_SkillService {
    private final Emergency_SkillRepository Emergency_SkillRepository;
    private final Gson gson;

    Emergency_SkillService(Emergency_SkillRepository Emergency_SkillRepository){
        this.Emergency_SkillRepository = Emergency_SkillRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    ResponseEntity<String> getEmergency_Skill() {
        List<Emergency_Skill> emergency_skill = Emergency_SkillRepository.getEmergency_Skills();
        return new ResponseEntity<>(
                gson.toJson(emergency_skill),
                HttpStatus.OK);
    }

    @PostMapping({"","/"})
    ResponseEntity<String> newEmergency_Skill(@RequestBody String request){
        Emergency_Skill es = gson.fromJson(request,Emergency_Skill.class);
        es = Emergency_SkillRepository.newEmergency_Skill(es);
        if(es != null && es.getId_skill() != null && es.getId_emergency() != null){
            return new ResponseEntity<>(
                    gson.toJson(es),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getEmergency_Skill(@PathVariable Long id){
        Emergency_Skill es = Emergency_SkillRepository.getEmergency_Skill(id);
        if(es != null){
            return new ResponseEntity<>(
                    gson.toJson(es),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editEmergency_Skill(@PathVariable Long id,@RequestBody Emergency_Skill request){
        Emergency_Skill es = Emergency_SkillRepository.getEmergency_Skill(id);
        if(request != null && es != null){
            if(request.getId_emergency() != null){
                es.setId_emergency(request.getId_emergency());
            }
            if(request.getId_skill() != null){
                es.setId_skill(request.getId_skill());
            }
            es = Emergency_SkillRepository.editEmergency_Skill(id, es);
            return new ResponseEntity<>(
                    gson.toJson(es),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmergency_Skill(@PathVariable Long id){
        if(Emergency_SkillRepository.deleteEmergency_Skill(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/recovery/{id}")
    ResponseEntity<String> recoveryEmergency_Skill(@PathVariable Long id){
        if(Emergency_SkillRepository.recoveryEmergency_Skill(id)){
            return new ResponseEntity<>(
                    gson.toJson(Emergency_SkillRepository.getEmergency_Skill(id)),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
