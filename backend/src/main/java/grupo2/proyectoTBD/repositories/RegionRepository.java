package grupo2.proyectoTBD.repositories;

import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Region;
import grupo2.proyectoTBD.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RegionRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Region> getRegions(){
        String sql =
                "SELECT gid,nom_reg FROM division_regional;";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Region.class);
        }
    }








}
