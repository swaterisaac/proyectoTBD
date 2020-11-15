package grupo2.proyectoTBD.models;

import java.sql.Timestamp;

public class Task {
    private Long id;
    private String name;
    private String description;
    private Integer volunteer_required;
    private Integer volunteer_registered;
    private Timestamp start_date;
    private Timestamp final_date;
    private String created_at;

    //foraneas
    private Long id_status;
    private Long id_emergency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


