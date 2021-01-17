package grupo2.proyectoTBD.models;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Document("taskMongo")
public class TaskMongo {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private Integer volunteer_required;
    private Integer volunteer_registered;
    private Date start_date;
    private Date final_date;
    private Date created_at;
    private Boolean deleted;
    private double longitude;
    private double latitude;

    //foraneas
    private Long id_status;
    private ObjectId id_emergency;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public Integer getVolunteer_required() {
        return volunteer_required;
    }

    public void setVolunteer_required(Integer volunteer_required) {
        this.volunteer_required = volunteer_required;
    }

    public Integer getVolunteer_registered() {
        return volunteer_registered;
    }

    public void setVolunteer_registered(Integer volunteer_registered) {
        this.volunteer_registered = volunteer_registered;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Date final_date) {
        this.final_date = final_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public Long getId_status() {
        return id_status;
    }

    public void setId_status(Long id_status) {
        this.id_status = id_status;
    }

    public ObjectId getId_emergency() {
        return id_emergency;
    }

    public void setId_emergency(ObjectId id_emergency) {
        this.id_emergency = id_emergency;
    }
}
