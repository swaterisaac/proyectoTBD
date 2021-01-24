package grupo2.proyectoTBD.repositories;

import grupo2.proyectoTBD.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RankingRepository {
    @Autowired
    private Sql2o sql2o;

    public List<Ranking> getRankings(){
        String sql =
                "SELECT * FROM rankings where deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Ranking.class);
        }
    }

    public Ranking newRanking(Ranking ranking){
        String sql = "INSERT INTO ranking(score,flg_invited,flg_participates,id_task,id_volunteer) " +
                "values (:score,:flg_invited,:flg_participates,:id_task,:id_volunteer)";
        Long id = null;
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    bind(ranking).executeUpdate().getKey(Long.class);
        }

        if(id != null){
            ranking.setId(id);
            return ranking;
        }
        return null;
    }

    public Ranking getRanking(Long id){
        String sql =
                "SELECT * FROM rankings where id = :id and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Ranking.class);
        }
    }

    public Ranking editRanking(Long id,Ranking ranking){
        String sql = "UPDATE rankings SET " +
                "score = :score ," +
                "flg_invited = :flg_invited ," +
                "flg_participates = :flg_participates," +
                "id_task = :id_task ," +
                "id_volunteer = :id_volunteer " +
                "WHERE id = :id and deleted = false";
        Long final_id = null;
        try(Connection con = sql2o.open()) {
            final_id = con.createQuery(sql,true).
                    bind(ranking).addParameter("id",id).executeUpdate().getKey(Long.class);
        }
        if(id != null){
            ranking.setId(final_id);
            return ranking;
        }
        return null;

    }

    public boolean deleteRanking(Long id){
        String sql = "UPDATE rankings SET delete = true WHERE id = :id and deleted= false";
        try(Connection con = sql2o.open()) {
            id = con.createQuery(sql,true).
                    addParameter("id",id)
                    .executeUpdate().getKey(Long.class);
        }
        return (id!=null);
    }

    public List<Ranking> getRankingsByTask(Long id_task){
        String sql =
                "SELECT * FROM rankings where id_task = :id_task and flg_invited = false and deleted = false";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).
                    addParameter("id_task",id_task)
                    .executeAndFetch(Ranking.class);
        }
    }


}
