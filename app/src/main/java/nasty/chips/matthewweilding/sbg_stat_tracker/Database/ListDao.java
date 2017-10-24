package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Dao
public interface ListDao {

    @Query("SELECT * FROM list")
    java.util.List<List> getAll();

    @Query("SELECT * FROM list WHERE modelId IN (:listIds)")
    java.util.List<List> loadAllByIds(int[] listIds);

    @Insert
    void insertAll(List... lists);

    @Delete
    void delete(List list);

}
