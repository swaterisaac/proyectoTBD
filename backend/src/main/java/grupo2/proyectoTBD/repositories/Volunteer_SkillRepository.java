package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Volunteer_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
public class Volunteer_SkillRepository{
    @Autowired
    private Sql2o sql2o;

    public List<Volunteer_Skill> getVolunteer_Skills(){
        String sql =
                "SELECT * FROM volunteers_skills where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Volunteer_Skill.class);
        }
    }
    
    public Volunteer_Skill newVolunteer_Skill(Volunteer_Skill vs){
        String sql = "INSERT INTO volunteers_skills(id_volunteer,id_skill) " +
                    "values (:id_volunteer,:id_skill)";
        Long id = null;
        try(Connection con = sql2o.open()){
            id = con.createQuery(sql,true).
                    bind(vs).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            vs.setId(id);
            return vs;
        }
        return null;
    }

    public Volunteer_Skill getVolunteer_Skill(Long id){
        String sql = "SELECT * FROM volunteers_skills where id = :id and deleted = false";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Volunteer_Skill.class);
        }
    }

    public Volunteer_Skill editVolunteer_Skill(Long id, Volunteer_Skill vs){
        String sql = "UPDATE volunteers_skills SET " +
                "id_skill = :id_skill, " +
                "id_volunteer = :id_volunteer " +   //AQUI ESTA EL ERROR AQUI ESTA EL ERROR AQUI ESTA EL ERROR (se arregla con una coma)
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()){
            final_id = con.createQuery(sql,true)
                    .bind(vs)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            vs.setId(final_id);
            return vs;
        }
        return null;
    }

    public Boolean deleteVolunteer_Skill(Long id){
        String sql = "UPDATE volunteers_skills SET deleted = true WHERE id = :id and deleted = false";
        try(Connection con = sql2o.open()){
            id = con.createQuery(sql, true)
                    .addParameter("id", id)
                    .executeUpdate().getKey(Long.class);
        }
        return id != null;
    }

    public Boolean recoveryVolunteer_Skill(Long id){
        String sql = "UPDATE volunteers_skills SET deleted = false WHERE id = :id and deleted = true";
        try(Connection con = sql2o.open()){
            id = con.createQuery(sql, true)
                    .addParameter("id", id)
                    .executeUpdate().getKey(Long.class);
        }
        return id != null;
    }

}