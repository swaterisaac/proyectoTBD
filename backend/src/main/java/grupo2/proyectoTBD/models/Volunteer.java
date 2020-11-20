package grupo2.proyectoTBD.models;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Volunteer implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("id_user")
    private Long id_user;
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

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
