package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Emergency;
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
                "SELECT *" + "FROM users where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(User.class);
        }
    }

    public List<User> getUsers(){
        String sql =
                "SELECT *" + "FROM users where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public User newUser(User user){
        String sql = "INSERT INTO users(rut,first_name,email,password,last_name,phone) values (:rut,:first_name,:email,:password,:last_name,:phone)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
            addParameter("rut",user.getRut())
            .addParameter("first_name",user.getFirst_name())
            .addParameter("email",user.getEmail())
            .addParameter("password",user.getPassword())
            .addParameter("last_name",user.getLast_name())
            .addParameter("phone",user.getPhone()).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            user.setId(id);
            return user;
        }
        return null;
    }


}