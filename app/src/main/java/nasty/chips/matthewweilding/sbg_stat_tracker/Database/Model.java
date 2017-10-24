package nasty.chips.matthewweilding.sbg_stat_tracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew.weilding on 23/10/2017.
 */

@Entity
public class Model {

    public Model(int modelId, String modelName, int fight, int shoot, int strength, int defence, int attacks, int wounds, int courage, int might, int will, int fate, boolean hero) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.fight = fight;
        this.shoot = shoot;
        this.strength = strength;
        this.defence = defence;
        this.attacks = attacks;
        this.wounds = wounds;
        this.courage = courage;
        this.might = might;
        this.will = will;
        this.fate = fate;
        this.hero = hero;
    }

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

    @ColumnInfo(name = "attacks")
    private int attacks;

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

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getFight() {
        return fight;
    }

    public void setFight(int fight) {
        this.fight = fight;
    }

    public int getShoot() {
        return shoot;
    }

    public void setShoot(int shoot) {
        this.shoot = shoot;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttacks() {
        return attacks;
    }

    public void setAttacks(int attacks) {
        this.attacks = attacks;
    }

    public int getWounds() {
        return wounds;
    }

    public void setWounds(int wounds) {
        this.wounds = wounds;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
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

    public boolean isHero() {
        return hero;
    }

    public void setHero(boolean hero) {
        this.hero = hero;
    }
}
