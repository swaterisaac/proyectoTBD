package grupo2.proyectoTBD.services;


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
@RestController
@RequestMapping("/emergency")
public class EmergencyService {

    private final EmergencyRepository EmergencyRepository;
    EmergencyService(EmergencyRepository EmergencyRepository){
        this.EmergencyRepository = EmergencyRepository;
    }


    @GetMapping("/{id}")
    public String getEmergency(@PathVariable Long id){
        Emergency e = EmergencyRepository.getEmergency(id);
        return e.getName();
    }
}