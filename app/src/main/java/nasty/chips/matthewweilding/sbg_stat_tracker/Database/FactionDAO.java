package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.*;

import java.util.List;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Dao
public interface FactionDAO {

    @Query("SELECT * FROM faction")
    List<Faction> getAll();

//    @Query("SELECT * FROM faction WHERE factionId IN (:factionIds)")
//    List<Faction> loadAllByIds(int[] factionIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Faction... factions);

//    @Delete
//    void delete(Faction factions);

}
