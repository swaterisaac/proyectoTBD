package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Ranking;
import grupo2.proyectoTBD.repositories.RankingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingService {

    private final RankingRepository RankingRepository;
    private final Gson gson;

    RankingService(RankingRepository RankingRepository){
        this.RankingRepository = RankingRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/")
    ResponseEntity<String> getRankings() {
        List<Ranking> rankings = RankingRepository.getRankings();
        return new ResponseEntity<>(
                gson.toJson(rankings),
                HttpStatus.OK);
    }


    @PostMapping("/")
    ResponseEntity<String> newRanking(@RequestBody String request){
        Ranking ranking = gson.fromJson(request,Ranking.class);
        if(ranking != null){
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
    ResponseEntity<String> editRanking(@PathVariable Long id,@RequestBody String request){
        Ranking ranking = gson.fromJson(request,Ranking.class);
        ranking = RankingRepository.editRanking(id,ranking);
        if(ranking != null){
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
