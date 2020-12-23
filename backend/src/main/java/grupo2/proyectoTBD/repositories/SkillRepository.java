package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class SkillRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Skill> getSkills(){
        String sql =
                "SELECT * FROM skills where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Skill.class);
        }
    }

    public Skill newSkill(Skill skill){
        String sql = "INSERT INTO skills(name,description) " +
                "values (:name,:description)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(skill).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            skill.setId(id);
            return skill;
        }
        return null;
    }

    public Skill getSkill(Long id){
        String sql =
                "SELECT * FROM skills where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Skill.class);
        }
    }

    public Skill editSkill(Long id,Skill skill){
        String sql = "UPDATE skills SET " +
                "name = :name, " +
                "description = :description " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true)
                    .bind(skill)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            skill.setId(final_id);
            return skill;
        }
        return null;
    }

    public boolean deleteSkill(Long id){
        String sql = "UPDATE skills SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }
}
