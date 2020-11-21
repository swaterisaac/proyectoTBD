package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.TaskUpdate;
import grupo2.proyectoTBD.models.User;
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
                "SELECT * FROM task_update where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(TaskUpdate.class);
        }
    }

    public List<TaskUpdate> getTasks(){
        String sql =
                "SELECT *  FROM task_update where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(TaskUpdate.class);
        }
    }

    public TaskUpdate newTaskUpdate(TaskUpdate taskUpdate){
        String sql = "INSERT INTO task_update(description,id_task,created_at) values (:description,:id_task,NOW())";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true)
                    .addParameter("description",taskUpdate.getDescription())
                    .addParameter("id_task", taskUpdate.getId_task()).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            taskUpdate.setId(id);
            return taskUpdate;
        }
        return null;
    }

    public TaskUpdate editTaskUp(Long id, TaskUpdate tu){
        String sql = "UPDATE task_update SET " +
                "description = :description, " +
                "id_task = :id_task " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true)
                    .bind(tu)
                    .addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        if(final_id != null){
            tu.setId(final_id);
            return tu;
        }
        return null;
    }

    public boolean deleteTaskUp(Long id){
        String sql = "UPDATE task_update SET deleted = true WHERE id = :id and deleted=false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }

}
