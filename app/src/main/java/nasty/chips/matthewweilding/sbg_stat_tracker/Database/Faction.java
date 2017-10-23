package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.*;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Entity
public class Faction {

    @PrimaryKey
    private int factionId;

    @ColumnInfo(name = "faction_name")
    private String factionName;

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }
}
