package grupo2.proyectoTBD.services;

import grupo2.proyectoTBD.models.TaskMongo;

import grupo2.proyectoTBD.repositories.TaskMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mongotask")
public class TaskMongoService {
    @Autowired
    private TaskMongoRepository taskRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody TaskMongo emergency) {
        taskRepo.save(emergency);
    }

    @RequestMapping(value = "/aggregate/{idEmergency}", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskMongo> aggregate(@PathVariable(value = "idEmergency") String idEmergency) {
        if(ObjectId.isValid(idEmergency)){
            ObjectId emergency = new ObjectId(idEmergency);
            return this.taskRepo.findTasksByIdEmergency(emergency).getMappedResults();
        }
        return null;
    }

}
