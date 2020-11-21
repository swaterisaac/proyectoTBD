package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Ranking;
import grupo2.proyectoTBD.repositories.RankingRepository;
import grupo2.proyectoTBD.repositories.TaskRepository;
import grupo2.proyectoTBD.repositories.VolunteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingService {

    private final RankingRepository RankingRepository;
    private final TaskRepository taskRepository;
    private final VolunteerRepository volunteerRepository;
    private final Gson gson;

    RankingService(RankingRepository RankingRepository, TaskRepository taskRepository, VolunteerRepository volunteerRepository){
        this.RankingRepository = RankingRepository;
        this.taskRepository = taskRepository;
        this.volunteerRepository = volunteerRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    ResponseEntity<String> getRankings() {
        List<Ranking> rankings = RankingRepository.getRankings();
        return new ResponseEntity<>(
                gson.toJson(rankings),
                HttpStatus.OK);
    }


    @PostMapping({"","/"})
    ResponseEntity<String> newRanking(@RequestBody String request){
        Ranking ranking = gson.fromJson(request,Ranking.class);
        if(ranking != null && ranking.getId_task() != null && taskRepository.getTask(ranking.getId_task()) != null && ranking.getId_volunteer() != null && volunteerRepository.getVolunteer(ranking.getId_volunteer()) != null){
            return new ResponseEntity<>(
                    gson.toJson(ranking),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getRanking(@PathVariable Long id){
        Ranking ranking = RankingRepository.getRanking(id);
        if(ranking != null){
            return new ResponseEntity<>(
                    gson.toJson(ranking),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editRanking(@PathVariable Long id,@RequestBody Ranking request){
        Ranking ranking = RankingRepository.getRanking(id);
        if(ranking != null && request != null){
            if(request.getId_volunteer() != null){
                if(volunteerRepository.getVolunteer(request.getId_volunteer()) == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                ranking.setId_volunteer(request.getId_volunteer());
            }
            if(request.getId_task() != null){
                if(taskRepository.getTask(request.getId_task()) == null){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                ranking.setId_volunteer(request.getId_task());
            }
            if(request.getScore() != null){
                ranking.setScore(request.getScore());
            }
            if(request.isFlg_invited()){
                ranking.setFlg_invited(request.isFlg_invited());
            }
            if(request.isFlg_participates()){
                ranking.setFlg_participates(request.isFlg_participates());
            }
            ranking = RankingRepository.editRanking(id,ranking);
            return new ResponseEntity<>(
                    gson.toJson(ranking),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteRanking(@PathVariable Long id){
        if(RankingRepository.deleteRanking(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
