package grupo2.proyectoTBD.services;


import grupo2.proyectoTBD.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import grupo2.proyectoTBD.repositories.EmergencyRepository;

import grupo2.proyectoTBD.models.Emergency;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emergency")
public class EmergencyService {

    private final EmergencyRepository EmergencyRepository;
    private final Gson gson;

    EmergencyService(EmergencyRepository EmergencyRepository){

        this.EmergencyRepository = EmergencyRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/{id}")
    public String getEmergency(@PathVariable Long id){
        Emergency emergency = EmergencyRepository.getEmergency(id);
        return gson.toJson(emergency);
    }

    @GetMapping("/getall")
    public String getEmergencies(){
        List<Emergency> emergencies = EmergencyRepository.getEmergencies();
        return gson.toJson(emergencies);
    }

    @PostMapping("/new")
    public String newEmergency(@RequestBody String request){
        Emergency emergency = gson.fromJson(request,Emergency.class);
        return gson.toJson(EmergencyRepository.newEmergency(emergency));
    }

    @PostMapping("/edit/{id}")
    public String editEmergency(@RequestBody String request, @PathVariable Long id){
        Emergency emergency = gson.fromJson(request,Emergency.class);
        Emergency eme = EmergencyRepository.getEmergency(id);

        if (emergency.getId_status() != null){
            eme.setId_status(emergency.getId_status());
        }

        if (emergency.getName() != null){
            eme.setName(emergency.getName());
        }

        if (emergency.getDescription() != null){
            eme.setDescription(emergency.getDescription());
        }

        if (emergency.getStart_date() != null){
            eme.setStart_date(emergency.getStart_date());
        }

        if (emergency.getFinal_date() != null){
            eme.setFinal_date(emergency.getFinal_date());
        }

        if (emergency.getId_institution() != null){
            eme.setId_institution(emergency.getId_institution());
        }

        if (emergency.getCreated_at() != null){
            eme.setCreated_at(emergency.getCreated_at());
        }

        return gson.toJson(EmergencyRepository.editEmergency(eme, id));
    }
}