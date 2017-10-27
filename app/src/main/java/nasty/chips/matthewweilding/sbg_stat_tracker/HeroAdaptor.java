package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

public class HeroAdaptor extends RecyclerView.Adapter<HeroAdaptor.HeroViewHolder> {

    private List<CurrentStats> models;
    Globals globals;

    HeroAdaptor(List<CurrentStats> models, Globals globals) {
        this.models = models;
        this.globals = globals;

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
            holder.modelName.setText(getName(position));

            holder.might.setText(String.valueOf(models.get(position).getMight()));
            holder.will.setText(String.valueOf(models.get(position).getWill()));
            holder.fate.setText(String.valueOf(models.get(position).getFate()));
            holder.wounds.setText(String.valueOf(models.get(position).getWounds()));

            setUpListeners(holder, position);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void setUpListeners(HeroViewHolder holder, final int position) {

        final CurrentStats model = models.get(position);

        holder.mightUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setMight(model.getMight()+1);
                upDateModel(model, position);

            }
        });

        holder.willUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setWill(model.getWill()+1);
                upDateModel(model, position);

            }
        });

        holder.fateUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setFate(model.getFate()+1);
                upDateModel(model, position);

            }
        });

        holder.woundsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setWounds(model.getWounds()+1);
                upDateModel(model, position);

            }
        });

        holder.mightDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setMight(model.getMight()-1);
                upDateModel(model, position);

            }
        });

        holder.willDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setWill(model.getWill()-1);
                upDateModel(model, position);

            }
        });

        holder.fateDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setFate(model.getFate()-1);
                upDateModel(model, position);


            }
        });

        holder.woundsDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setWounds(model.getWounds()-1);
                upDateModel(model, position);

            }
        });

    }

    public void upDateModel(CurrentStats model, int position){

        globals.db.currentStatsDao().insertAll(model);
        HeroAdaptor.this.notifyItemChanged(position);

    }

    private String getName(int position){

        if(models.get(position).getCustomName().equals(""))
        {
            return globals.db.modelDao().getName(models.get(position).getModelId());
        }
        else
        {
            return models.get(position).getCustomName();
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

        TextView modelName, might, will, fate, wounds;
        Button mightUp, willUp, fateUp, woundsUp, mightDown, willDown, fateDown, woundsDown;

        HeroViewHolder(View itemView) {
            super(itemView);
            modelName = itemView.findViewById(R.id.name);
            might = itemView.findViewById(R.id.might);
            will = itemView.findViewById(R.id.will);
            fate = itemView.findViewById(R.id.fate);
            wounds = itemView.findViewById(R.id.wounds);
            mightUp = itemView.findViewById(R.id.mightUp);
            willUp = itemView.findViewById(R.id.willUp);
            fateUp = itemView.findViewById(R.id.fateUp);
            woundsUp = itemView.findViewById(R.id.woundsUp);
            mightDown = itemView.findViewById(R.id.mightDown);
            willDown = itemView.findViewById(R.id.willDown);
            fateDown = itemView.findViewById(R.id.fateDown);
            woundsDown = itemView.findViewById(R.id.woundsDown);

        }

    }
}
