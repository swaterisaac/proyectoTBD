package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class User implements Serializable{

	@SerializedName("id")
    private Long id;
	@SerializedName("rut")
    private String rut;
	@SerializedName("first_name")
    private String first_name;
	@SerializedName("email")
    private String email;
	@SerializedName("password")
    private String password;
	@SerializedName("last_name")
    private String last_name;
	@SerializedName("phone")
	private String phone; 
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

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}