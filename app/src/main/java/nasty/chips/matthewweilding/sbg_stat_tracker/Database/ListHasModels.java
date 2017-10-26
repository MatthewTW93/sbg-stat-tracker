package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Entity(indices = @Index(value = {"list_id", "model_id"}),
        foreignKeys = {@ForeignKey(entity = ArmyList.class,
        parentColumns = "listId",
        childColumns = "list_id"),
        @ForeignKey(entity = Model.class,
                parentColumns = "modelId",
                childColumns = "model_id")})
public class ListHasModels {

    @PrimaryKey(autoGenerate = true)
    private int listHasModelsId;

    @ColumnInfo(name = "list_id")
    private int listId;

    @ColumnInfo(name = "model_id")
    private String modelId;

    @ColumnInfo(name = "custom_name")
    private String customName;

    public int getListHasModelsId() {
        return listHasModelsId;
    }

    public void setListHasModelsId(int listHasModelsId) {
        this.listHasModelsId = listHasModelsId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
