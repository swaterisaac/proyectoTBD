package grupo2.proyectoTBD.services;

import grupo2.proyectoTBD.models.EmergencyMongo;

import grupo2.proyectoTBD.repositories.EmergencyMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mongo")
public class EmergencyMongoService {
    @Autowired
    private EmergencyMongoRepository emergencyRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody EmergencyMongo emergency) {

        emergencyRepo.save(emergency);
    }

}
