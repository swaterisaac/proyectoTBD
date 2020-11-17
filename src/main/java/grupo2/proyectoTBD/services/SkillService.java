package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Skill;
import grupo2.proyectoTBD.repositories.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillService {

    private final SkillRepository SkillRepository;
    private final Gson gson;

    SkillService(SkillRepository SkillRepository){
        this.SkillRepository = SkillRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/")
    ResponseEntity<String> getSkills() {
        List<Skill> skills = SkillRepository.getSkills();
        return new ResponseEntity<>(
                gson.toJson(skills),
                HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<String> newSkill(@RequestBody String request){
        Skill skill = gson.fromJson(request,Skill.class);
        if(skill != null){
            return new ResponseEntity<>(
                    gson.toJson(skill),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getSkill(@PathVariable Long id){
        Skill skill = SkillRepository.getSkill(id);
        if(skill != null){
            return new ResponseEntity<>(
                    gson.toJson(skill),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editSkill(@PathVariable Long id,@RequestBody String request){
        Skill skill = gson.fromJson(request,Skill.class);
        skill = SkillRepository.editSkill(id,skill);
        if(skill != null){
            return new ResponseEntity<>(
                    gson.toJson(skill),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteSkill(@PathVariable Long id){
        if(SkillRepository.deleteSkill(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
