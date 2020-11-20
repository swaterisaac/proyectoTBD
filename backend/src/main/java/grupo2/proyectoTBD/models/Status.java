package grupo2.proyectoTBD.models;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Status implements Serializable{
	@SerializedName("id")
	private Long id;
	@SerializedName("description")
	private String description;
	@SerializedName("deleted")
	private Boolean deleted;

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}