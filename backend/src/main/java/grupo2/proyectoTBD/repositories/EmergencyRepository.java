package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.User;
import org.sql2o.Sql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
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

    public Emergency getEmergency(Long id){
        String sql =
                "SELECT *" + "FROM emergencies where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Emergency.class);
        }
    }

    public List<Emergency> getEmergencies(){
        String sql =
                "SELECT *" + "FROM emergencies where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Emergency.class);
        }
    }

    public Emergency newEmergency(Emergency emergency){
        String sql = "INSERT INTO emergencies(id_status, name, description, start_date, final_date, id_institution, created_at) values (:id_status, :name, :description, :start_date, :final_date, :id_institution, :created_at)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id_status",emergency.getId_status())
                    .addParameter("name",emergency.getName())
                    .addParameter("description",emergency.getDescription())
                    .addParameter("start_date",emergency.getStart_date())
                    .addParameter("final_date",emergency.getFinal_date())
                    .addParameter("id_institution",emergency.getId_institution())
                    .addParameter("created_at",emergency.getCreated_at()).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            emergency.setId(id);
            return emergency;
        }
        return null;
    }

    public Emergency editEmergency(Emergency emergency, Long id){
        String sql = "UPDATE emergencies " +
                "SET id_status = :id_status, name = :name, description = :description, start_date = :start_date, final_date = :final_date, id_institution = :id_institution, created_at = :created_at, deleted = :deleted " +
                "WHERE id = :id";

        try(Connection con = sql2o.open()) {
            con.createQuery(sql, true).bind(emergency).addParameter("id",id)
                    .executeUpdate();
            return getEmergency(id);
        }
    }

    public boolean deleteEmergency(Long id){
        String sql = "UPDATE emergencies SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }
}