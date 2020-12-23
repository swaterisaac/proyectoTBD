package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable{

	@SerializedName("id")
    private Long id;
	@SerializedName("nombre")
    private String nombre;
	@SerializedName("apellido")
    private String apellido;
	@SerializedName("email")
    private String email;
	@SerializedName("dimensions")
	private String dimensions;
	@SerializedName("requirements")
	private String requirements;
	@SerializedName("longitude")
	private double longitude;
	@SerializedName("latitude")
	private double latitude;
	@SerializedName("age")
	private Integer age;
	@SerializedName("deleted")
	private Boolean deleted;
	@SerializedName("password")
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}