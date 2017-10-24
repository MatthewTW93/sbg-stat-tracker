package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Database(entities = {Faction.class, FactionsHaveModels.class, Model.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FactionDAO factionDAO();
    public abstract FactionsHaveModelsDao factionHasModelsDao();
    public abstract ModelDao modelDao();
}