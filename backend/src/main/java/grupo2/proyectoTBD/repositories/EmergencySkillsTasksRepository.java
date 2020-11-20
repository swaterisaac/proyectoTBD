package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Emergency_Skills_Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergencySkillsTasksRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Emergency_Skills_Tasks> getEmeSkiTasks(){
        String sql =
                "SELECT * FROM emergency_skills_tasks where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Emergency_Skills_Tasks.class);
        }
    }

    public Emergency_Skills_Tasks newEmeSkiTas(Emergency_Skills_Tasks emeskilltask){
        String sql = "INSERT INTO emergency_skills_tasks(id_eme_skills,id_task) " +
                "values (:id_eme_skills,:id_task)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(emeskilltask).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            emeskilltask.setId(id);
            return emeskilltask;
        }
        return null;
    }

    public Emergency_Skills_Tasks getEmeSkiTas(Long id){
        String sql =
                "SELECT * FROM emergency_skills_tasks where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Emergency_Skills_Tasks.class);
        }
    }

    public Emergency_Skills_Tasks editEmeSkiTas(Long id,Emergency_Skills_Tasks emeskilltask){
        String sql = "UPDATE emergency_skills_tasks SET " +
                "id_eme_skills = :id_eme_skills" +
                "id_task = :id_task " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true)
                    .bind(emeskilltask)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            emeskilltask.setId(final_id);
            return emeskilltask;
        }
        return null;
    }

    public boolean deleteEmeSkiTas(Long id){
        String sql = "UPDATE emergency_skills_tasks SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }
}
