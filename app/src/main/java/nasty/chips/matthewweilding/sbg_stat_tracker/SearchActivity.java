package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import javax.annotation.Nullable;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Faction;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 25/10/2017.
 */

public class SearchActivity extends Activity {

    RecyclerView rv;
    Globals globals;
    boolean faction = false;
    FactionAdaptor factionAdaptor;
    ModelSelectAdaptor modelSelectAdaptor;
    int pageFrom = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        rv = findViewById(R.id.searchRecycler);
        globals = new Globals();
        globals.setUpDb(this);

        Bundle b = getIntent().getExtras();
        if(b != null)
            pageFrom = b.getInt("page");

        Log.e("Search", "In the search");

        setUpSearch(null);
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

        boolean orientation = (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
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

        LinearLayoutManager llm = new LinearLayoutManager(this);
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
                modelSelectAdaptor.notifyItemChanged(position, model);
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
