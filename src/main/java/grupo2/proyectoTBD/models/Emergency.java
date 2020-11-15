package grupo2.proyectoTBD.models;

import java.sql.Timestamp;
import java.util.Date;

public class Emergency {
    private Long id;
    private Long id_status;  //No se si esta bien poner ese tipo de dato en las fk
    private String name;
    private String description;
    private Date start_date; //No se que tipo de dato es date.
    private Date final_date;
    private Long id_institution;
    private Timestamp created_at;

//... Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_status() {
        return id_status;
    }

    public void setId_status(Long id_status) {
        this.id_status = id_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Date final_date) {
        this.final_date = final_date;
    }

    public Long getId_institution() {
        return id_institution;
    }

    public void setId_institution(Long id_institution) {
        this.id_institution = id_institution;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}