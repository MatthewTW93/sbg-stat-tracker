package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Dao
public interface CurrentStatsDao {

//    @Query("SELECT * FROM currentStats")
//    java.util.List<CurrentStats> getAll();
//
//    @Query("SELECT * FROM currentStats WHERE currentStatsId IN (:currentStatsId)")
//    java.util.List<CurrentStats> loadAllByIds(int[] currentStatsId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(CurrentStats... currentStats);

    @Query("SELECT DISTINCT model_id FROM currentStats "+
            "WHERE side = (:side)")
    int[] distinctModels(int side);

    @Query("SELECT c.* FROM currentStats c " +
            "INNER JOIN model m ON modelId = model_id "+
            "WHERE side = (:side) " +
            "AND (m.hero = 1 OR m.wounds > 1)")
    List<CurrentStats> getHeroes(int side);

    @Query("DELETE FROM currentstats " +
            "WHERE side = (:side)")
    void deleteSide(int side);
//
//    @Delete
//    void delete(CurrentStats currentStats);
}
