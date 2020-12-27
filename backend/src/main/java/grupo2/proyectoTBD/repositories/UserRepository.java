package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.Skill;
import grupo2.proyectoTBD.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private Sql2o sql2o;

    public User getUser(Long id){
        String sql =
                "SELECT id,nombre,apellido,email,sexo,age,password,deleted,st_y(st_astext(location)) AS latitude, st_x(st_astext(location)) AS longitude " +
                "FROM users where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(User.class);
        }
    }

    public List<User> getUsers(){
        String sql =
                "SELECT id,nombre,apellido,email,sexo,age,password,deleted,st_y(st_astext(location)) AS latitude, st_x(st_astext(location)) AS longitude " +
                "FROM users where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public User newUser(User user){
        String point = user.getLongitude().toString() + " " + user.getLatitude().toString();
        String sql = "INSERT INTO users(nombre,apellido,email,sexo,location,age,password) " +
                "values (:nombre,:apellido,:email,:sexo,ST_GeomFromText('POINT(" + point + ")', 4326),:age,:password)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true)
            .addParameter("nombre",user.getNombre())
            .addParameter("apellido",user.getApellido())
            .addParameter("email",user.getEmail())
            .addParameter("sexo",user.getSexo())
            .addParameter("age",user.getAge())
            .addParameter("password",user.getPassword())
            .executeUpdate().getKey(Long.class);
        }

        if(id != null){
            user.setId(id);
            return user;
        }
        return null;
    }
    public User editUser(Long id, User user){
        String point = user.getLongitude().toString() + " " + user.getLatitude().toString();
        String sql = "UPDATE users SET " +
                "nombre = :nombre, " +
                "apellido = :apellido, " +
                "email = :email, " +
                "sexo = :sexo, " +
                "location =  ST_GeomFromText('POINT(" + point + ")', 4326), " +
                "age = :age, " +
                "password = :password " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true)
                    .bind(user)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            user.setId(final_id);
            return user;
        }
        return null;
    }

    public boolean deleteUser(Long id){
        String sql = "UPDATE users SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }


}
