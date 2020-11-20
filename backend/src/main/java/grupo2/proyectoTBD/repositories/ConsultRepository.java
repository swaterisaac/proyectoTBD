package grupo2.proyectoTBD.repositories;

import com.google.gson.JsonObject;
import grupo2.proyectoTBD.models.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class ConsultRepository {
    @Autowired
    private Sql2o sql2o;

    public JsonObject consult(Long emid, int age){
        String sql = "SELECT COUNT(*), ta.name\n" +
                "FROM volunteers v,users u, emergencies e, status s, rankings r, tasks ta\n" +
                "WHERE u.age >= :age AND v.deleted = false\n" +
                "AND e.id = :emid AND e.id_status = s.id AND s.description = 'Activo'\n" +
                "AND :emid = ta.id_emergency\n" +
                "AND ta.id = r.id_task AND r.id_volunteer = v.id\n" +
                "AND v.id = u.id\n" +
                "GROUP BY  ta.name;\n";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchFirst(JsonObject.class);
        }
        finally {
            return null;
        }

    }

}
