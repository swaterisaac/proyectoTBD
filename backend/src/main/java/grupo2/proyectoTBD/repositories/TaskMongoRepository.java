package grupo2.proyectoTBD.repositories;


import grupo2.proyectoTBD.models.EmergencyMongo;
import grupo2.proyectoTBD.models.TaskMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskMongoRepository extends MongoRepository<TaskMongo, String> {
    @Aggregation(pipeline = {"{$match: {id_emergency:?0}}"})
    AggregationResults<TaskMongo> findTasksByIdEmergency(ObjectId idEmergency);
}
