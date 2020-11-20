package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitutionRepository {
    @Autowired
    private Sql2o sql2o;

    public Institution getInstitution(Long id){
        String sql =
                "SELECT *" + "FROM institutions where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Institution.class);
        }
    }

    public List<Institution> getInstitutions(){
        String sql =
                "SELECT *" + "FROM institutions where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Institution.class);
        }
    }

    public Institution newInstitution(Institution inst){
        String sql = "INSERT INTO institutions(name) values (:name)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("name",inst.getName())
                    .executeUpdate().getKey(Long.class);
        }

        if(id != null){
            inst.setId(id);
            return inst;
        }
        return null;
    }

    public Institution editInstitution(Institution inst, Long id){
        String sql = "UPDATE institutions " +
                "SET name = :name " +
                "WHERE id = :id";

        try(Connection con = sql2o.open()) {
            con.createQuery(sql, true).bind(inst).addParameter("id",id)
                    .executeUpdate();
            return getInstitution(id);
        }
    }

    public boolean deleteInstitution(Long id){
        String sql = "UPDATE institutions SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }
}
