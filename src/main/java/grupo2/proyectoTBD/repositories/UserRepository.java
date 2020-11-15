package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Emergency;
import grupo2.proyectoTBD.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class UserRepository {
    @Autowired
    private Sql2o sql2o;

    public User getUser(Long id){
        String sql =
                "SELECT *" + "FROM users where id = %s".formatted(id);
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchFirst(User.class);
        }
    }
}
