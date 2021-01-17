package grupo2.proyectoTBD.services;

import grupo2.proyectoTBD.models.EmergencyMongo;

import grupo2.proyectoTBD.models.TaskMongo;
import grupo2.proyectoTBD.repositories.EmergencyMongoRepository;
import grupo2.proyectoTBD.repositories.TaskMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
