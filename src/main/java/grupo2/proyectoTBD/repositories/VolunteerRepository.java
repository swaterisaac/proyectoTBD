package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VolunteerRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Volunteer> getVolunteeres(){
        String sql =
                "SELECT * FROM volunteers where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Volunteer.class);
        }
    }

    public Volunteer newVolunteer(Volunteer volunteer){
        String sql = "INSERT INTO volunteers(id_user) " +
                "values (:id_user)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(volunteer).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            volunteer.setId(id);
            return volunteer;
        }
        return null;
    }

    public Volunteer getVolunteer(Long id){
        String sql =
                "SELECT * FROM volunteers where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Volunteer.class);
        }
    }

    public Volunteer editVolunteer(Long id,Volunteer volunteer){
        String sql = "UPDATE volunteers SET " +
                "id_user = :id_user " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true)
                    .bind(volunteer)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            volunteer.setId(final_id);
            return volunteer;
        }
        return null;
    }

    public boolean deleteVolunteer(Long id){
        String sql = "UPDATE volunteers SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }
}
