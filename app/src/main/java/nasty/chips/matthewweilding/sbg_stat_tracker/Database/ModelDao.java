package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Dao
public interface ModelDao {

    @Query("SELECT * FROM model")
    List<Model> getAll();

    @Query("SELECT * FROM model WHERE modelId = (:modelId)")
    Model getOneFromId(int modelId);

    @Query("SELECT * FROM model WHERE modelId IN (:modelIds)")
    List<Model> loadAllByIds(int[] modelIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Model... models);

//    @Delete
//    void delete(Model models);

    @Query("SELECT * FROM model m "+
            "INNER JOIN factionsHaveModels j ON m.modelId = j.model_id "+
            "WHERE j.faction_id = (:factionId)")
    List<Model> getFromFaction(int factionId);


    @Query("SELECT * FROM model m "+
            "INNER JOIN factionsHaveModels j ON m.modelId = j.model_id "+
            "WHERE (j.faction_id = (:factionId))"+
            "AND (m.hero = 1 OR m.wounds > 1)")
    List<Model> getHeroesFromFaction(int factionId);
}
