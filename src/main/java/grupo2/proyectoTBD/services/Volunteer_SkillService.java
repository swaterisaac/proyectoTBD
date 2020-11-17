package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Status;
import grupo2.proyectoTBD.models.Volunteer_Skill;
import grupo2.proyectoTBD.repositories.Volunteer_SkillRepository;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/volunteer-skill")
public class Volunteer_SkillService{
    private final Volunteer_SkillRepository Volunteer_SkillRepository;
    private final Gson gson;

    Volunteer_SkillService(Volunteer_SkillRepository Volunteer_SkillRepository){
        this.Volunteer_SkillRepository = Volunteer_SkillRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    ResponseEntity<String> getVolunteer_Skill() {
        List<Volunteer_Skill> volunteer_skill = Volunteer_SkillRepository.getVolunteer_Skills();
        return new ResponseEntity<>(
                gson.toJson(volunteer_skill),
                HttpStatus.OK);
    }


    @PostMapping({"","/"})
    ResponseEntity<String> newVolunteer_Skill(@RequestBody String request){
        Volunteer_Skill vk = gson.fromJson(request,Volunteer_Skill.class);
        vk = Volunteer_SkillRepository.newVolunteer_Skill(vk);
        if(vk != null && vk.getId_skill() != null && vk.getId_volunteer() != null){
            return new ResponseEntity<>(
                gson.toJson(vk),
                HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getEmergency_Skill(@PathVariable Long id){
        Volunteer_Skill vk = Volunteer_SkillRepository.getVolunteer_Skill(id);
        if(vk != null){
            return new ResponseEntity<>(
                    gson.toJson(vk),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editVolunteer_Skill(@PathVariable Long id,@RequestBody Volunteer_Skill request){
        Volunteer_Skill vs = Volunteer_SkillRepository.getVolunteer_Skill(id);
        if(request != null && vs != null){
            if(request.getId_volunteer() != null){
                vs.setId_volunteer(request.getId_volunteer());
            }
            if(request.getId_skill() != null){
                vs.setId_skill(request.getId_skill());
            }
            vs = Volunteer_SkillRepository.editVolunteer_Skill(id, vs);
            return new ResponseEntity<>(
                    gson.toJson(vs),
                    HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteVolunteer_Skill(@PathVariable Long id){
        if(Volunteer_SkillRepository.deleteVolunteer_Skill(id)){
            return new ResponseEntity<>(
                HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/recovery/{id}")
    ResponseEntity<String> recoveryVolunteer_Skill(@PathVariable Long id){
        if(Volunteer_SkillRepository.recoveryVolunteer_Skill(id)){
            return new ResponseEntity<>(
                gson.toJson(Volunteer_SkillRepository.getVolunteer_Skill(id)),
                HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}