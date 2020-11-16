package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Ranking;
import grupo2.proyectoTBD.repositories.RankingRepository;
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
    public String getRankings() {
        List<Ranking> rankings = RankingRepository.getRankings();
        return gson.toJson(rankings);
    }


    @PostMapping("/")
    public String newRanking(@RequestBody String request){
        Ranking ranking = gson.fromJson(request,Ranking.class);
        return gson.toJson(RankingRepository.newRanking(ranking));
    }

    @GetMapping("/{id}")
    public String getRanking(@PathVariable Long id){
        Ranking ranking = RankingRepository.getRanking(id);
        return gson.toJson(ranking);
    }

    @PutMapping("/{id}")
    public String editRanking(@PathVariable Long id,@RequestBody String request){
        Ranking ranking = gson.fromJson(request,Ranking.class);
        ranking = RankingRepository.editRanking(id,ranking);
        return gson.toJson(ranking);
    }

    @DeleteMapping("/{id}")
    public void deleteRanking(@PathVariable Long id){
        RankingRepository.deleteRanking(id);
    }

}
