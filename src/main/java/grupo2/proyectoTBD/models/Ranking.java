package grupo2.proyectoTBD.models;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Ranking implements Serializable{
    @SerializedName("id")
    private Long id;
    @SerializedName("score")
    private Integer score;
    @SerializedName("flg_invited")
    private boolean flg_invited;
    @SerializedName("flg_participates")
    private boolean flg_participates;
    @SerializedName("id_task")
    private Long id_task;
    @SerializedName("id_volunteer")
    private Long id_volunteer;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isFlg_invited() {
        return flg_invited;
    }

    public void setFlg_invited(boolean flg_invited) {
        this.flg_invited = flg_invited;
    }

    public boolean isFlg_participates() {
        return flg_participates;
    }

    public void setFlg_participates(boolean flg_participates) {
        this.flg_participates = flg_participates;
    }

    public Long getId_task() {
        return id_task;
    }

    public void setId_task(Long id_task) {
        this.id_task = id_task;
    }

    public Long getId_volunteer() {
        return id_volunteer;
    }

    public void setId_volunteer(Long id_volunteer) {
        this.id_volunteer = id_volunteer;
    }
}
