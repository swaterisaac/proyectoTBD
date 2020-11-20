package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;

public class Emergency_Skill {
    @SerializedName("id")
    private Long id;
    @SerializedName("id_emergency")
    private Long id_emergency;
    @SerializedName("id_skill")
    private Long id_skill;
    @SerializedName("delete")
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_emergency() {
        return id_emergency;
    }

    public void setId_emergency(Long id_emergency) {
        this.id_emergency = id_emergency;
    }

    public Long getId_skill() {
        return id_skill;
    }

    public void setId_skill(Long id_skill) {
        this.id_skill = id_skill;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
