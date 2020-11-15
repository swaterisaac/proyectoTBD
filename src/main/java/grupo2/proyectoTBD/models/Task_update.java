package grupo2.proyectoTBD.models;

import java.sql.Timestamp;

public class Task_update {
    private Long id;
    private Timestamp created_at;
    private String description;
    //foranea
    private Long id_task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
