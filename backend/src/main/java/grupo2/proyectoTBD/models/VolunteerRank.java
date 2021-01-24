package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;

public class VolunteerRank {
    @SerializedName("id")
    private Long id;
    @SerializedName("score")
    private Integer score;
    @SerializedName("nombre")
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
