package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Entity(foreignKeys = @ForeignKey(entity = Model.class,
        parentColumns = "id",
        childColumns = "model_id"))
public class CurrentStats {

    @PrimaryKey
    private int currentStatsId;

    @ColumnInfo(name = "model_id")
    private int modelId;

    @ColumnInfo(name = "wounds")
    private int wounds;

    @ColumnInfo(name = "might")
    private int might;

    @ColumnInfo(name = "will")
    private int will;

    @ColumnInfo(name = "fate")
    private int fate;

}
