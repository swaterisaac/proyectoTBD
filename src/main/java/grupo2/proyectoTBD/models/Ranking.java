package grupo2.proyectoTBD.models;

public class Ranking {
    private Long id;
    private Integer score;
    private boolean flg_invited;
    private boolean flg_participates;
    private Long id_task;
    private Long id_volunteer;

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
