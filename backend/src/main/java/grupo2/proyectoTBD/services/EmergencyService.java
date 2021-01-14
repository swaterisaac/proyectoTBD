package grupo2.proyectoTBD.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo2.proyectoTBD.repositories.InstitutionRepository;
import grupo2.proyectoTBD.repositories.StatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import grupo2.proyectoTBD.repositories.EmergencyRepository;

import grupo2.proyectoTBD.models.Emergency;

import java.util.List;

@RestController
@RequestMapping("/emergency")
@CrossOrigin(origins = "*")

public class EmergencyService {

    private final EmergencyRepository EmergencyRepository;
    private final InstitutionRepository institutionRepository;
    private final StatusRepository statusRepository;
    private final Gson gson;

    EmergencyService(EmergencyRepository EmergencyRepository, InstitutionRepository institutionRepository, StatusRepository statusRepository){

        this.EmergencyRepository = EmergencyRepository;
        this.institutionRepository = institutionRepository;
        this.statusRepository = statusRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GetMapping("/{id}")
    ResponseEntity<String> getEmergency(@PathVariable Long id){
        Emergency emergency = EmergencyRepository.getEmergency(id);

        if(emergency != null){
            return new ResponseEntity<>(
                    gson.toJson(emergency),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping({"","/"})
    ResponseEntity<String> getEmergencies() {
        List<Emergency> emergencies = EmergencyRepository.getEmergencies();
        return new ResponseEntity<>(
                gson.toJson(emergencies),
                HttpStatus.OK);
    }

    @PostMapping({"","/"})
    ResponseEntity<String> newEmergency(@RequestBody String request){
        Emergency emergency = gson.fromJson(request,Emergency.class);

        if(emergency != null && emergency.getId_institution() != null && emergency.getFinal_date() != null && emergency.getDescription() != null && emergency.getId_status() != null && emergency.getName() != null && emergency.getStart_date() != null && emergency.getLongitude() != null && emergency.getLatitude() != null && statusRepository.getStatus(emergency.getId_status()) != null && institutionRepository.getInstitution(emergency.getId_institution()) != null) {
            emergency = EmergencyRepository.newEmergency(emergency);
            return new ResponseEntity<>(
                    gson.toJson(emergency),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> editEmergency(@RequestBody String request, @PathVariable Long id){
        Emergency emergency = gson.fromJson(request,Emergency.class);
        Emergency eme = EmergencyRepository.getEmergency(id);

        if (emergency.getId_status() != null){
            //Comprobación llave foránea
            if(statusRepository.getStatus(emergency.getId_status()) == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
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
            if(institutionRepository.getInstitution(emergency.getId_institution()) == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            eme.setId_institution(emergency.getId_institution());
        }

        if (emergency.getLongitude() != null){
            eme.setLongitude(emergency.getLongitude());
        }

        if (emergency.getLatitude() != null){
            eme.setLatitude(emergency.getLatitude());
        }

        eme = EmergencyRepository.editEmergency(eme, id);

        if(eme != null){
            return new ResponseEntity<>(
                    gson.toJson(eme),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmergency(@PathVariable Long id){
        if(EmergencyRepository.deleteEmergency(id)){
            return new ResponseEntity<>(
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lastId")
    ResponseEntity<String> getLastId(){
        return new ResponseEntity<String>(
                EmergencyRepository.lastid()+"",
                HttpStatus.NOT_FOUND);
    }

}