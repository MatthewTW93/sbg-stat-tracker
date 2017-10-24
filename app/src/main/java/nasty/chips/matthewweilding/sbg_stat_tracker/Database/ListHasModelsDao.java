package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Dao
public interface ListHasModelsDao {

    @Query("SELECT * FROM listHasModels")
    java.util.List<ListHasModels> getAll();
//
//    @Query("SELECT * FROM listHasModels WHERE modelId IN (:modelIds)")
//    java.util.ArmyList<ListHasModels> loadAllByIds(int[] modelIds);

    @Insert
    void insertAll(ListHasModels... listHasModels);

    @Delete
    void delete(ListHasModels listHasModels);

}
