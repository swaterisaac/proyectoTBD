package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("emergencyMongo")
public class EmergencyMongo {
    @Id
    private ObjectId _id;
    @SerializedName("id_status")
    private String id_status;
    private String name;
    private String description;
   // private Date start_date;
    //private Date final_date;
    private String id_institution;
    //private Timestamp created_at;
    private String deleted;
    private String longitude;
    private String latitude;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getId_status() {
        return id_status;
    }

    public void setId_status(String id_status) {
        this.id_status = id_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_institution() {
        return id_institution;
    }

    public void setId_institution(String id_institution) {
        this.id_institution = id_institution;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
