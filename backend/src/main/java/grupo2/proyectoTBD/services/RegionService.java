package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Region;
import grupo2.proyectoTBD.models.User;
import grupo2.proyectoTBD.repositories.RegionRepository;
import grupo2.proyectoTBD.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
@CrossOrigin(origins = "*")

public class RegionService {

    private final grupo2.proyectoTBD.repositories.RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final Gson gson;

    RegionService(RegionRepository regionRepository, UserRepository userRepository){
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    ResponseEntity<List<Region>>getAllRegions(){
        return new ResponseEntity<List<Region>>(
                this.regionRepository.getRegions(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getEmergency(@PathVariable Long id){
        Region region = this.regionRepository.getRegion(id);
        if(region != null){
            return new ResponseEntity<>(
                    gson.toJson(region),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{regionId}")
    ResponseEntity<String> getUsersFromRegion(@PathVariable Long regionId){
        List<User> users = this.regionRepository.getUsers(regionId);
        return new ResponseEntity<String>(
                gson.toJson(users),
                HttpStatus.OK);
    }
}
