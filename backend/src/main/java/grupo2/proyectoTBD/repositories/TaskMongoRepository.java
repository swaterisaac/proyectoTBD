package grupo2.proyectoTBD.repositories;


import grupo2.proyectoTBD.models.TaskMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskMongoRepository extends MongoRepository<TaskMongo, String> {
}
