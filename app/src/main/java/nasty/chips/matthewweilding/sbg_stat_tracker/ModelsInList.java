package nasty.chips.matthewweilding.sbg_stat_tracker;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 26/10/2017.
 *
 * ModelsInList is used to store a Model and it's selected status for the search activity
 */

public class ModelsInList {

    private boolean checked = false;
    private Model model;

    ModelsInList(Model model) {
        this.model = model;
    }

    boolean isChecked() {
        return checked;
    }

    void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
