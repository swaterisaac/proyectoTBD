package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Emergency_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Emergency_SkillRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Emergency_Skill> getEmergency_Skills(){
        String sql = "SELECT * FROM emergency_skills where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Emergency_Skill.class);
        }
    }

    public Emergency_Skill newEmergency_Skill(Emergency_Skill es){
        String sql = "INSERT INTO emergency_skills(id_emergency,id_skill) " +
                "values (:id_emergency,:id_skill)";
        Long id = null;
        try(Connection con = sql2o.open()){
            id = con.createQuery(sql,true).
                    bind(es).executeUpdate().getKey(Long.class);
        }
        if(id != null){
            es.setId(id);
            return es;
        }
        return null;
    }

    public Emergency_Skill getEmergency_Skill(Long id){
        String sql = "SELECT * FROM emergency_skills where id = :id and deleted = false";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Emergency_Skill.class);
        }
    }

    public Emergency_Skill editEmergency_Skill(Long id, Emergency_Skill es){
        String sql = "UPDATE emergency_skills SET " +
                "id_emergency = :id_emergency, " +
                "id_skill = :id_skill " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()){
            final_id = con.createQuery(sql,true)
                    .bind(es)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            es.setId(final_id);
            return es;
        }
        return null;
    }

    public Boolean deleteEmergency_Skill(Long id){
        String sql = "UPDATE emergency_skills SET deleted = true WHERE id = :id and deleted = false";
        try(Connection con = sql2o.open()){
            id = con.createQuery(sql, true)
                    .addParameter("id", id)
                    .executeUpdate().getKey(Long.class);
        }
        return id != null;
    }

    public Boolean recoveryEmergency_Skill(Long id){
        String sql = "UPDATE emergency_skills SET deleted = false WHERE id = :id and deleted = true";
        try(Connection con = sql2o.open()){
            id = con.createQuery(sql, true)
                    .addParameter("id", id)
                    .executeUpdate().getKey(Long.class);
        }
        return id != null;
    }
}
