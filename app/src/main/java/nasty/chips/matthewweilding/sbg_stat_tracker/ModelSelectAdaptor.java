package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 25/10/2017.
 */

public class ModelSelectAdaptor extends RecyclerView.Adapter<ModelSelectAdaptor.ModelViewHolder>{

    List<Model> models;
    ArrayList<Integer> selection;

    public ModelSelectAdaptor(List<Model> models) {
        this.models = models;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_model_select, parent, false);
        return new ModelSelectAdaptor.ModelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {

        try{

            holder.modelName.setText(models.get(position).getModelName());

            if(selection.contains(models.get(position).getModelId()))
            {
                holder.selected.setSelected(true);
            }


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

    public void setSelected(Model model){

        selection.add(model.getModelId());

    }

    public void removeSelected(Model model){

        selection.remove(model.getModelId());

    }

    public ArrayList<Integer> getSelectedIds(){
        return selection;
    }

    class ModelViewHolder extends RecyclerView.ViewHolder{

        TextView modelName;
        CheckBox selected;

        public ModelViewHolder(View itemView) {
            super(itemView);

            modelName = itemView.findViewById(R.id.modelName);
            selected = itemView.findViewById(R.id.modelCheckBox);

        }
    }

}
