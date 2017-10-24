package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Globals;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

public class UnitStatsFragment extends Fragment {

    boolean opponent;
    RecyclerView rv;
    List<Model> models;
    Globals globals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_unit_stats, container, false);
        opponent = getArguments().getBoolean("opponent");
        rv = rootView.findViewById(R.id.unitRecycler);
        globals = new Globals();
        globals.setUpDb(getActivity());

// TODO: 24/10/2017 This isn't needed
        TextView tv = new TextView(getActivity());
        tv.setText("Unit Page: "+opponent);

        LinearLayout ll = rootView.findViewById(R.id.unit_background);
        ll.addView(tv);

        (rootView.findViewById(R.id.unit_background)).setBackgroundColor(opponent?Color.RED:Color.BLUE);

        setUpList();

        return rootView;
    }

    void setUpList(){

        int[] modelIds = globals.db.currentStatsDao().destinctModels();

        models = globals.db.modelDao().loadAllByIds(modelIds);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        UnitAdaptor unitAdaptor = new UnitAdaptor(models, getContext());
        rv.setAdapter(unitAdaptor);

        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };

        rv.setItemAnimator(animator);

    }

}
