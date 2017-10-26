package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Entity(indices = @Index(value = "model_id"),
        foreignKeys = @ForeignKey(entity = Model.class,
        parentColumns = "modelId",
        childColumns = "model_id"))
public class CurrentStats {

    public CurrentStats(int currentStatsId, int modelId, String customName, int wounds, int might, int will, int fate, int side) {
        this.currentStatsId = currentStatsId;
        this.modelId = modelId;
        this.customName = customName;
        this.wounds = wounds;
        this.might = might;
        this.will = will;
        this.fate = fate;
        this.side = side;
    }

    @PrimaryKey(autoGenerate = true)
    private int currentStatsId;

    @ColumnInfo(name = "model_id")
    private int modelId;

    @ColumnInfo(name = "custom_name")
    private String customName;

    @ColumnInfo(name = "wounds")
    private int wounds;

    @ColumnInfo(name = "might")
    private int might;

    @ColumnInfo(name = "will")
    private int will;

    @ColumnInfo(name = "fate")
    private int fate;

    @ColumnInfo(name = "side")
    private int side;

    public int getCurrentStatsId() {
        return currentStatsId;
    }

    public void setCurrentStatsId(int currentStatsId) {
        this.currentStatsId = currentStatsId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getMight() {
        return might;
    }

    public void setMight(int might) {
        this.might = might;
    }

    public int getWill() {
        return will;
    }

    public void setWill(int will) {
        this.will = will;
    }

    public int getFate() {
        return fate;
    }

    public void setFate(int fate) {
        this.fate = fate;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
}
