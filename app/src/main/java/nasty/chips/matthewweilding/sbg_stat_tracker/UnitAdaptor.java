package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

class UnitAdaptor extends RecyclerView.Adapter<UnitAdaptor.UnitViewHolder> {

    private List<Model> models;

    UnitAdaptor(List<Model> models) {
        this.models = models;

    }

    @Override
    public UnitAdaptor.UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_model_stats, parent, false);
        return new UnitAdaptor.UnitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UnitAdaptor.UnitViewHolder holder, int position) {

        try
        {
            Model model = models.get(position);

            holder.modelName.setText(model.getModelName());
            holder.fight.setText(model.getFight());
            holder.shoot.setText(model.getShoot());
            holder.strength.setText(model.getStrength());
            holder.defence.setText(model.getDefence());
            holder.attacks.setText(model.getAttacks());
            holder.wounds.setText(model.getWounds());
            holder.courage.setText(model.getCourage());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public int getPosition(CurrentStats model) {
        return models.indexOf(model);
    }

    public void removeItem(CurrentStats model) {
        models.remove(model);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    class UnitViewHolder extends RecyclerView.ViewHolder{

        TextView modelName, move, fight, shoot, strength, defence, attacks, wounds, courage;

        public UnitViewHolder(View itemView) {
            super(itemView);
            modelName = itemView.findViewById(R.id.name);
            move = itemView.findViewById(R.id.move);
            fight = itemView.findViewById(R.id.fight);
            shoot = itemView.findViewById(R.id.shoot);
            strength = itemView.findViewById(R.id.strength);
            defence = itemView.findViewById(R.id.defence);
            attacks = itemView.findViewById(R.id.attacks);
            wounds = itemView.findViewById(R.id.wounds);
            courage = itemView.findViewById(R.id.courage);
        }

    }
}
