package grupo2.proyectoTBD.repositories;

import org.sql2o.Sql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

@Repository
public class PersonRepositoryImp{

    @Autowired
    private Sql2o sql2o;

    public int countPersons() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM persons";
        try(Connection conn = sql2o.open()){
            total = conn.createQuery(sql).executeScalar(Integer.class);
        }
        return total;
    }
}