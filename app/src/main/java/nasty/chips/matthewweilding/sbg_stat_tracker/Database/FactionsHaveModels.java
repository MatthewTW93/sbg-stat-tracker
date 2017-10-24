package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Faction.class,
        parentColumns = "id",
        childColumns = "faction_id"),
        @ForeignKey(entity = Model.class,
        parentColumns = "id",
        childColumns = "model_id")})
public class FactionsHaveModels {

    @ColumnInfo(name = "faction_id")
    private int factionId;

    @ColumnInfo(name = "model_id")
    private int modelId;

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
