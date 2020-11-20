package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class StatusRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Status> getStatuses(){
        String sql =
                "SELECT * FROM status where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Status.class);
        }
    }

    public Status newStatus(Status status){
        String sql = "INSERT INTO status(description) " +
                "values (:description)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(status).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            status.setId(id);
            return status;
        }
        return null;
    }

    public Status getStatus(Long id){
        String sql =
                "SELECT * FROM status where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Status.class);
        }
    }

    public Status editStatus(Long id,Status status){
        String sql = "UPDATE status SET " +
                "description = :description " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true)
                    .bind(status)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            status.setId(final_id);
            return status;
        }
        return null;
    }

    public boolean deleteStatus(Long id){
        String sql = "UPDATE status SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }


}
