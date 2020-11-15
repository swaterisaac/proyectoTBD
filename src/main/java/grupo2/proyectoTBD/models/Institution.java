package grupo2.proyectoTBD.models;

public class Institution{
	@SerializedName("id")
	private Long id;
	@SerializedName("name")
	private String name;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}