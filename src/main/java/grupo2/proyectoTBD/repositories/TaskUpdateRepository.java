package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.TaskUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TaskUpdateRepository {
    @Autowired
    private Sql2o sql2o;

    public TaskUpdate getTask(Long id){
        String sql =
                "SELECT *" + "FROM tasks where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(TaskUpdate.class);
        }
    }

    public List<TaskUpdate> getTasks(){
        String sql =
                "SELECT *" + "FROM tasks where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(TaskUpdate.class);
        }
    }

    public TaskUpdate newTaskUpdate(TaskUpdate taskUpdate){
        String sql = "INSERT INTO tasks(name,description,volunteer_required,volunteer_registered,start_date,final_date,created_at) values (:name,:description,:volunteer_required,:volunteer_registered,:start_date,:final_date,:created_at)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("created_at",taskUpdate.getCreated_at())
                    .addParameter("description",taskUpdate.getDescription())
                    .addParameter("id_task", taskUpdate.getId_task()).executeUpdate().getKey(Long.class); //----->>> REVISAR
        }

        if(id != null){
            taskUpdate.setId(id);
            return taskUpdate;
        }
        return null;
    }
}
