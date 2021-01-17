package grupo2.proyectoTBD.services;

import grupo2.proyectoTBD.models.TaskMongo;

import grupo2.proyectoTBD.repositories.TaskMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
