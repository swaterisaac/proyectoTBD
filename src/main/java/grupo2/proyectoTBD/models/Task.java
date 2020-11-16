package grupo2.proyectoTBD.models;

import java.sql.Timestamp;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Task implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("volunteer_required")
    private Integer volunteer_required;
    @SerializedName("volunteer_registred")
    private Integer volunteer_registered;
    @SerializedName("start_date")
    private Timestamp start_date;
    @SerializedName("final_date")
    private Timestamp final_date;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("delete")
    private Boolean deleted;

    //foraneas
    @SerializedName("id_status")
    private Long id_status;
    @SerializedName("id_emergency")
    private Long id_emergency;

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getVolunteer_required() {
        return volunteer_required;
    }

    public void setVolunteer_required(Integer volunteer_required) {
        this.volunteer_required = volunteer_required;
    }

    public Integer getVolunteer_registered() {
        return volunteer_registered;
    }

    public void setVolunteer_registered(Integer volunteer_registered) {
        this.volunteer_registered = volunteer_registered;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Timestamp final_date) {
        this.final_date = final_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Long getId_status() {
        return id_status;
    }

    public void setId_status(Long id_status) {
        this.id_status = id_status;
    }

    public Long getId_emergency() {
        return id_emergency;
    }

    public void setId_emergency(Long id_emergency) {
        this.id_emergency = id_emergency;
    }
}


