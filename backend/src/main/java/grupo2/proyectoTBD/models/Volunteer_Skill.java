package grupo2.proyectoTBD.models;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Volunteer_Skill implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("id_volunteer")
    private Long id_volunteer;
    @SerializedName("id_skill")
    private Long id_skill;
    @SerializedName("delete")
    private Boolean deleted;

    //Getters
	public Long getId() {
		return this.id;
    }
    
	public Long getId_volunteer() {
		return this.id_volunteer;
    }

    public Long getId_skill() {
		return this.id_skill;
	}
    public Boolean getDeleted(){
        return this.deleted;
    }


    //Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setId_volunteer(Long id_volunteer) {
		this.id_volunteer = id_volunteer;
    }
	public void setId_skill(Long id_skill) {
		this.id_skill = id_skill;
	}
    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
    }

}
