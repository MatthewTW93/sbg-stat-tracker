package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Entity(indices = @Index(value = {"faction_id", "model_id"}),
        foreignKeys = {@ForeignKey(entity = Faction.class,
        parentColumns = "factionId",
        childColumns = "faction_id"),
        @ForeignKey(entity = Model.class,
        parentColumns = "modelId",
        childColumns = "model_id")})
public class FactionsHaveModels {

    public FactionsHaveModels(int factionHaveModelsId, int factionId, int modelId) {
        this.factionHaveModelsId = factionHaveModelsId;
        this.factionId = factionId;
        this.modelId = modelId;
    }

    @PrimaryKey
    private int factionHaveModelsId;

    @ColumnInfo(name = "faction_id")
    private int factionId;

    @ColumnInfo(name = "model_id")
    private int modelId;

    public int getFactionHaveModelsId() {
        return factionHaveModelsId;
    }

    public void setFactionHaveModelsId(int factionHaveModelsId) {
        this.factionHaveModelsId = factionHaveModelsId;
    }

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
