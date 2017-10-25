package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.annotation.Nullable;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Faction;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 25/10/2017.
 */

public class SearchFragment extends Fragment {

    RecyclerView rv;
    Globals globals;
    boolean faction = false;
    FactionAdaptor factionAdaptor;
    ModelSelectAdaptor modelSelectAdaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        rv = rootView.findViewById(R.id.searchRecycler);
        globals = new Globals();
        globals.setUpDb(getActivity());

        Log.e("Search", "In the search");

        setUpSearch(null);

        return rootView;

    }

    void setUpSearch(@Nullable Faction currentFaction){

        if(!faction)
        {
            setUpFactions();
        }
        else
        {
            setUpModels(currentFaction);
        }


        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };

        rv.setItemAnimator(animator);

    }

    void setUpFactions(){

        boolean orientation = (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(
                orientation?4:2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(mStaggeredLayoutManager);
        factionAdaptor = new FactionAdaptor(globals.db.factionDAO().getAll());
        rv.setAdapter(factionAdaptor);
        RecyclerViewItemClickSupport.addTo(rv).setOnItemClickListener(new RecyclerViewItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                faction = true;
                setUpSearch(factionAdaptor.getItemAtPosition(position));

            }
        });

    }


    void setUpModels(Faction currentFaction){

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        modelSelectAdaptor = new ModelSelectAdaptor(globals.db.modelDao().getFromFaction(currentFaction.getFactionId()));
        rv.setAdapter(modelSelectAdaptor);
        RecyclerViewItemClickSupport.addTo(rv).setOnItemClickListener(new RecyclerViewItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Model model = modelSelectAdaptor.getItemAtPosition(position);

                if (modelSelectAdaptor.selected(position))
                {
                    modelSelectAdaptor.removeSelected(model);
                }
                else
                {
                    modelSelectAdaptor.setSelected(model);
                }
            }
        });
    }

    void addSelected(){

        CurrentStats newStats[] = new CurrentStats[modelSelectAdaptor.getSelectedModels().size()];

        int counter = 0;

        for ( Model model : modelSelectAdaptor.getSelectedModels() )
        {
            newStats[counter] = new CurrentStats(0, model.getModelId(), "", model.getWounds(), model.getMight(), model.getWill(), model.getFate());
            counter++;
        }

        globals.db.currentStatsDao().insertAll(newStats);

        MainActivity mainActivity = new MainActivity();

        mainActivity.getmViewPager().setCurrentItem(1);

    }

}
