package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TaskRepository {
    @Autowired
    private Sql2o sql2o;

    public Task getTask(Long id){
        String sql =
                "SELECT *" + "FROM tasks where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Task.class);
        }
    }

    public List<Task> getTasks(){
        String sql =
                "SELECT *" + "FROM tasks where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Task.class);
        }
    }

    public Task newTask(Task task){
        String sql = "INSERT INTO tasks(name,description,volunteer_required,volunteer_registered,start_date,final_date,created_at, id_status, id_emergency) values (:name,:description,:volunteer_required,:volunteer_registered,:start_date,:final_date,NOW(),:id_status, :id_emergency)";
        Long id;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(task).executeUpdate().getKey(Long.class);
        }

        if(id != null){ //
            task.setId(id);
            return task;
        }
        return getTask(id);
    }

    //edita una tupla de task en la base de datos
    public Task editTask(Task task, Long id){
        String updateSql = "UPDATE tasks SET name = :name, description = :description, volunteer_required = :volunteer_required, volunteer_registered = :volunteer_registered, start_date = :start_date, final_date = :final_date, created_at = :created_at WHERE id = :id";
        try(Connection con = sql2o.open())  {
            con.createQuery(updateSql).bind(task).addParameter("id", id).executeUpdate();
        }
        return getTask(id);
    }

    public boolean deleteTask(Long id){
        String updateSql = "UPDATE tasks SET deleted = true WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("id", id).executeUpdate();
        }
        return (id!=null);
    }

    public List<Task> getEmergencyTasks(Long emergency_id){
        String sql =
                "SELECT *" + "FROM tasks where deleted = false and emergency_id = :emergency_id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).
                    addParameter("emergency_id",emergency_id)
                    .executeAndFetch(Task.class);
        }
    }
}
