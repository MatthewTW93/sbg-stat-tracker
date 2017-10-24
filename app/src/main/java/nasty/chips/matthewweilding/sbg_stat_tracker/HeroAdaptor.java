package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Globals;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

public class HeroAdaptor extends RecyclerView.Adapter<HeroAdaptor.HeroViewHolder> {

    private List<CurrentStats> models;
    Context context;

    HeroAdaptor(List<CurrentStats> models, Context context) {
        this.models = models;
        this.context = context;

    }

    @Override
    public HeroAdaptor.HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_hero_stats, parent, false);
        return new HeroAdaptor.HeroViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeroAdaptor.HeroViewHolder holder, int position) {

        try
        {
            Globals globals = new Globals();
            globals.setUpDb(context);

            CurrentStats current = models.get(position);

            Model model = globals.db.modelDao().getOneFromId(current.getModelId());



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


    class HeroViewHolder extends RecyclerView.ViewHolder{

        TextView modelName;
        TextView might;
        TextView will;
        TextView fate;
        TextView wounds;

        public HeroViewHolder(View itemView) {
            super(itemView);
            modelName = itemView.findViewById(R.id.name);
            might = itemView.findViewById(R.id.might);
            will = itemView.findViewById(R.id.will);
            fate = itemView.findViewById(R.id.fate);
            wounds = itemView.findViewById(R.id.wounds);
        }

    }
}
