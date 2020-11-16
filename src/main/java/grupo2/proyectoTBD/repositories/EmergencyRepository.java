package grupo2.proyectoTBD.repositories;

import org.sql2o.Sql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import grupo2.proyectoTBD.models.Emergency;


@Repository
public class EmergencyRepository{

    @Autowired
    private Sql2o sql2o;

    // public List<Emergency> getAllEmergencies(){
    //     String sql =
    //     "SELECT *" +
    //     "FROM emergencies";

    //     try(Connection con = sql2o.open()) {
    //         return con.createQuery(sql).executeAndFetch(Emergency.class);
    //     }
    // }

    public Emergency getEmergency(Long id){
        String sql =
        "SELECT *" +
        "FROM emergencies where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Emergency.class);
        }
    }

}