package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Region;
import grupo2.proyectoTBD.repositories.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionService {

    private final grupo2.proyectoTBD.repositories.RegionRepository regionRepository;
    private final Gson gson;

    RegionService(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    ResponseEntity<List<Region>>getAllRegions(){
        return new ResponseEntity<List<Region>>(
                this.regionRepository.getRegions(),
                HttpStatus.OK);
    }
}
