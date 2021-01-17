package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.EmergencyMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface EmergencyMongoRepository extends MongoRepository<EmergencyMongo, String> {

}

