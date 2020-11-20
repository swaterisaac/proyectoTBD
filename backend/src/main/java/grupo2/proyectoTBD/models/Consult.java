package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Consult implements Serializable {
    @SerializedName("count")
    Long count;
    @SerializedName("name")
    String name;

    public Consult(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
