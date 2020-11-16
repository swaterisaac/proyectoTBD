package grupo2.proyectoTBD.models;

import java.sql.Timestamp;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Task_update implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("created_at")
    private Timestamp created_at;
    @SerializedName("description")
    private String description;
    //foranea
    @SerializedName("id_task")
    private Long id_task;
    @SerializedName("deleted")
    private Boolean deleted;

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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }
}
