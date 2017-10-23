package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Entity
public class Model {

    @PrimaryKey
    private int modelId;

    @ColumnInfo(name = "model_name")
    private String modelName;

    @ColumnInfo(name = "fight")
    private int fight;

    @ColumnInfo(name = "shoot")
    private int shoot;

    @ColumnInfo(name = "strength")
    private int strength;

    @ColumnInfo(name = "defence")
    private int defence;

    @ColumnInfo(name = "wounds")
    private int wounds;

    @ColumnInfo(name = "courage")
    private int courage;

    @ColumnInfo(name = "might")
    private int might;

    @ColumnInfo(name = "will")
    private int will;

    @ColumnInfo(name = "fate")
    private int fate;

    @ColumnInfo(name = "hero")
    private boolean hero;





}
