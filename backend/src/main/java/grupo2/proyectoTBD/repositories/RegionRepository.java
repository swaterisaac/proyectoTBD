package grupo2.proyectoTBD.repositories;

import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Region;
import grupo2.proyectoTBD.models.Skill;
import grupo2.proyectoTBD.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class    RegionRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Region> getRegions(){
        String sql =
                "SELECT gid,nom_reg,st_y(st_astext(ST_centroid(geom))) AS latitude,st_x(st_astext(ST_centroid(geom))) AS longitude FROM division_regional;";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Region.class);
        }
    }

    public Region getRegion(Long id){
        String sql =
                "SELECT gid,nom_reg "  +
                "FROM division_regional where gid = :id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Region.class);
        }
    }
    public List<User> getUsers(Long regionId){
        String sql = "SELECT id, nombre,apellido,email, st_y(st_astext(location)) AS latitude,st_x(st_astext(location)) AS longitude" +
                " FROM users AS u " +
                "INNER JOIN division_regional AS r " +
                "ON ST_WITHIN(u.location, r.geom) " +
                "WHERE r.gid = :regionId";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
            .addParameter("regionId",regionId)
            .executeAndFetch(User.class);
        }
    }








}
