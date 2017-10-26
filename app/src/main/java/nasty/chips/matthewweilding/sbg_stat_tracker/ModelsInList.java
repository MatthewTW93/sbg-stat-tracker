package nasty.chips.matthewweilding.sbg_stat_tracker;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 26/10/2017.
 */

public class ModelsInList {

    boolean checked = false;
    Model model;

    public ModelsInList(Model model) {
        this.model = model;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
