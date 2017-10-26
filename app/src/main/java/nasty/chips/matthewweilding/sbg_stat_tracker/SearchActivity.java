package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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
    boolean heroesOnly = false;
    int side = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rv = findViewById(R.id.searchRecycler);
        globals = new Globals();
        globals.setUpDb(this);

        Bundle b = getIntent().getExtras();
        if(b != null)
            pageFrom = b.getInt("page");

        if(pageFrom == 1 || pageFrom == 2)
        {
            heroesOnly = true;
        }

        if(pageFrom >= 2)
        {
            side = 1;
        }

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

        findViewById(R.id.addButton).setVisibility(View.VISIBLE);

        //findViewById(R.id.buttonLayout).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 60));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        modelSelectAdaptor = new ModelSelectAdaptor(getModelsInList(currentFaction));
        rv.setAdapter(modelSelectAdaptor);
        RecyclerViewItemClickSupport.addTo(rv).setOnItemClickListener(new RecyclerViewItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                ModelsInList model = modelSelectAdaptor.getItemAtPosition(position);

                Log.d("Checked", "Checked: "+model.isChecked());

                if (model.isChecked())
                {
                    model.setChecked(false);
                }
                else
                {
                    model.setChecked(true);
                }

                modelSelectAdaptor.notifyItemChanged(position, model);

            }
        });
    }

    public ArrayList<ModelsInList> getModelsInList(Faction currentFaction)    {

        ArrayList<ModelsInList> modelsInLists = new ArrayList<>();

        for (Model model : heroOrAll(currentFaction))
        {
            modelsInLists.add(new ModelsInList(model));
        }

        Log.e("ModelsInLists Size", ""+ modelsInLists.size());

        return modelsInLists;

    }

    public List<Model> heroOrAll(Faction currentFaction){

        if (heroesOnly)
        {
            return globals.db.modelDao().getHeroesFromFaction(currentFaction.getFactionId());
        }
        else
        {
            return globals.db.modelDao().getFromFaction(currentFaction.getFactionId());
        }

    }

    public void addClicked(View view){

        addSelected();

    }

    void addSelected(){

        Log.e("AddSelected", "Hit Add Selected");

        CurrentStats newStats[] = new CurrentStats[modelSelectAdaptor.getSelectedModels().size()];

        int counter = 0;

        for ( Model model : modelSelectAdaptor.getSelectedModels() )
        {
            newStats[counter] = new CurrentStats(0, model.getModelId(), "", model.getWounds(), model.getMight(), model.getWill(), model.getFate(), side);
            counter++;
        }

        globals.db.currentStatsDao().insertAll(newStats);

        toMainActivity();

    }

    @Override
    public void onBackPressed() {

        if(!faction)
        {
            toMainActivity();
        }
        else
        {
            faction = false;
            Bundle b = new Bundle();
            b.putInt("page", pageFrom);
            onCreate(b);
        }

    }


    public void toMainActivity(){

        Intent intent = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putInt("page", pageFrom);
        Log.e("PageLog", "pageFrom: "+pageFrom);
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }

}
