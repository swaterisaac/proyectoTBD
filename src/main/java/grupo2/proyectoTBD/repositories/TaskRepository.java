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
        String sql = "INSERT INTO tasks(name,description,volunteer_required,volunteer_registered,start_date,final_date,created_at, id_status, id_emergency) values (:name,:description,:volunteer_required,:volunteer_registered,:start_date,:final_date,:created_at, :id_status, :id_emergency)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(task).executeUpdate().getKey(Long.class);
        }

        if(id != null){ //DUDA
            task.setId(id);
            return task;
        }
        return null;
    }

    //edita una tupla de task en la base de datos
    public Task editTask(Task task){
        String updateSql = "UPDATE tasks SET name = :name, description = :description, volunteer_required = :volunteer_required, volunteer_registered = :volunteer_registered, start_date = :start_date, final_date = :final_date, created_at = :created_at WHERE id = :id";
        try(Connection con = sql2o.open())  {
            con.createQuery(updateSql)
                .addParameter("name",task.getName())
                .addParameter("description",task.getDescription())
                .addParameter("volunteer_required",task.getVolunteer_required())
                .addParameter("volunteer_registered",task.getVolunteer_registered())
                .addParameter("start_date",task.getStart_date())
                .addParameter("final_date",task.getFinal_date())
                .addParameter("created_at", task.getCreated_at())
                .executeUpdate();
        }
        return null;
    }
}
