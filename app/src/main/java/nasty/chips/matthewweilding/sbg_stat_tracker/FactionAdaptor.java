package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Faction;

/**
 * Created by matthew.weilding on 25/10/2017.
 */

class FactionAdaptor extends RecyclerView.Adapter<FactionAdaptor.FactionViewHolder>{

    List<Faction> factions;

    public FactionAdaptor(List<Faction> factions) {
        this.factions = factions;
    }

    @Override
    public FactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_hero_stats, parent, false);
        return new FactionAdaptor.FactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FactionViewHolder holder, int position) {

        try{

            holder.faction.setText(factions.get(position).getFactionName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return factions.size();
    }

    public Faction getItemAtPosition(int position)
    {
        return factions.get(position);
    }

    public int getPosition(Faction faction) {
        return factions.indexOf(faction);
    }

    class FactionViewHolder extends RecyclerView.ViewHolder{

        TextView faction;

        public FactionViewHolder(View itemView) {
            super(itemView);
            faction = itemView.findViewById(R.id.factionTextView);
        }

    }
}
