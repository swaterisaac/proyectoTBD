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
        int age = request.get("age").getAsInt();
        Long emid = request.get("emid").getAsLong();

        String sql = "SELECT COUNT(*), ta.name\n" +
                "FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta\n" +
                "WHERE u.age >= :age AND v.deleted = false\n" +
                "AND e.id = :emid AND e.id_status = s.id AND s.description = 'Activo'\n" +
                "AND :emid = ta.id_emergency\n" +
                "AND ta.id = r.id_task AND r.id_volunteer = v.id\n" +
                "AND v.id = u.id\n" +
                "GROUP BY  ta.name ORDER BY ta.name ASC;\n";
        try(Connection con = sql2o.open()) {
            List<Consult> ola =  con.createQuery(sql)
                    .addParameter("emid", emid)
                    .addParameter("emid", emid)
                    .addParameter("age", age)
                    .executeAndFetch(Consult.class);
            return ola;
        }
    }

}
