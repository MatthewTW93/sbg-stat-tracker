package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
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

    @Insert
    void insertAll(Model... models);

    @Delete
    void delete(Model models);

}
