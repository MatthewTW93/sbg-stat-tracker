package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Entity(foreignKeys = {@ForeignKey(entity = List.class,
        parentColumns = "id",
        childColumns = "list_id"),
        @ForeignKey(entity = Model.class,
                parentColumns = "id",
                childColumns = "model_id")})
public class ListHasModels {

    @ColumnInfo(name = "list_id")
    private int listId;

    @ColumnInfo(name = "model_id")
    private String modelId;

    @ColumnInfo(name = "custom_name")
    private String customName;

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
