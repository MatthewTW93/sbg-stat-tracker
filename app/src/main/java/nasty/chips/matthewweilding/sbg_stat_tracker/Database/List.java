package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

@Entity
public class List {

    @PrimaryKey
    private int listId;

    @ColumnInfo(name = "list_name")
    private String listName;

    @ColumnInfo(name = "date_created")
    private Date dateCreated;

    @ColumnInfo(name = "opponents")
    private boolean opponents;

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isOpponents() {
        return opponents;
    }

    public void setOpponents(boolean opponents) {
        this.opponents = opponents;
    }
}
