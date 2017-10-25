
















package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Dao
public interface CurrentStatsDao {

    @Query("SELECT * FROM currentStats")
    java.util.List<CurrentStats> getAll();

    @Query("SELECT * FROM currentStats WHERE currentStatsId IN (:currentStatsId)")
    java.util.List<CurrentStats> loadAllByIds(int[] currentStatsId);

    @Query("SELECT DISTINCT currentStatsId FROM currentStats")
    int[] destinctModels();

    @Insert
    void insertAll(CurrentStats... currentStats);

    @Delete
    void delete(CurrentStats currentStats);
}
