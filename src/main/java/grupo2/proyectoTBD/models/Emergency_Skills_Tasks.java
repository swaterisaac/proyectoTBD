package grupo2.proyectoTBD.models;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Emergency_Skills_Tasks implements Serializable {
    @SerializedName("id")
    private Long id;
    @SerializedName("id_eme_skills")
    private Long id_eme_skills;
    @SerializedName("id_task")
    private Long id_task;
    @SerializedName("deleted")
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_eme_skills() {
        return id_eme_skills;
    }

    public void setId_eme_skills(Long id_eme_skills) {
        this.id_eme_skills = id_eme_skills;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
