package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Dao
public interface ArmyListDao {

    @Query("SELECT * FROM ArmyList")
    java.util.List<ArmyList> getAll();

    @Query("SELECT * FROM ArmyList WHERE listId IN (:listIds)")
    java.util.List<ArmyList> loadAllByIds(int[] listIds);

    @Insert
    void insertAll(ArmyList... lists);

    @Delete
    void delete(ArmyList list);

}
