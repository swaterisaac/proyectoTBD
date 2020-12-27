package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Region implements Serializable {
    @SerializedName("gid")
    private Long gid;
    @SerializedName("nom_reg")
    private String nom_reg;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("latitude")
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getNom_reg() {
        return nom_reg;
    }

    public void setNom_reg(String nom_reg) {
        this.nom_reg = nom_reg;
    }
}
