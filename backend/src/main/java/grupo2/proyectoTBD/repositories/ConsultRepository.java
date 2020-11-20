package grupo2.proyectoTBD.repositories;

import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Consult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class ConsultRepository {
    @Autowired
    private Sql2o sql2o;

    /*
    @Bean
    public ConsultRepository consultRepository() {
        return new ConsultRepository();
    }
*/
    public List<Consult> consult(JsonObject request){
        String sql = "SELECT COUNT(*), ta.name\n" +
                "FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta\n" +
                "WHERE u.age >= 15 AND v.deleted = false\n" +
                "AND e.id = 1 AND e.id_status = s.id AND s.description = 'Activo'\n" +
                "AND 1 = ta.id_emergency\n" +
                "AND ta.id = r.id_task AND r.id_volunteer = v.id\n" +
                "AND v.id = u.id\n" +
                "GROUP BY  ta.name ORDER BY ta.name ASC;\n";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Consult.class);
        }
    }

}
