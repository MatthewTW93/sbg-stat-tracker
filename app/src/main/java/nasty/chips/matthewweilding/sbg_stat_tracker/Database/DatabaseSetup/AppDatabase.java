package nasty.chips.matthewweilding.sbg_stat_tracker.Database.DatabaseSetup;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.ArmyList;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.ArmyListDao;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStatsDao;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Faction;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.FactionDAO;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.FactionsHaveModels;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.FactionsHaveModelsDao;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.ListHasModels;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.ListHasModelsDao;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.ModelDao;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Database(entities = {Faction.class, FactionsHaveModels.class, Model.class, ArmyList.class, ListHasModels.class, CurrentStats.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract FactionDAO factionDAO();
    public abstract FactionsHaveModelsDao factionHasModelsDao();
    public abstract ModelDao modelDao();
    public abstract ArmyListDao listDao();
    public abstract ListHasModelsDao listHasModelsDao();
    public abstract CurrentStatsDao currentStatsDao();
}