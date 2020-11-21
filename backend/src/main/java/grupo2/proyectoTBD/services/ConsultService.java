package grupo2.proyectoTBD.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Consult;
import grupo2.proyectoTBD.models.Ranking;
import grupo2.proyectoTBD.repositories.ConsultRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consult")
public class ConsultService {
    private final ConsultRepository consultRepository;
    private final Gson gson;

    ConsultService(ConsultRepository consultRepository){
        this.consultRepository = consultRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping({"","/"})
    public ResponseEntity<String> consult(@RequestBody String request){
        JsonObject jo = gson.fromJson(request, JsonObject.class);
        try {
            jo.get("emid").getAsLong();
            jo.get("age").getAsInt();
            List<Consult> jr = consultRepository.consult(jo);
            if(!jr.isEmpty()) {
                return new ResponseEntity<>(
                        gson.toJson(jr),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
        catch(Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
