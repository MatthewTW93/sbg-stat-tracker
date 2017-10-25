package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

public class Globals {

    public AppDatabase db;

    public void setUpDb(Context context)
    {
        db = Room.databaseBuilder(context,
                AppDatabase.class, "model_stats").allowMainThreadQueries().build();
    }





}
