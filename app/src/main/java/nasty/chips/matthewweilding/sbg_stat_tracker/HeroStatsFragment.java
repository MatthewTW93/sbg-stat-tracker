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

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.CurrentStats;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

public class HeroStatsFragment extends Fragment {

    boolean opponent;
    RecyclerView rv;
    Globals globals;
    List<CurrentStats> models;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hero_stats, container, false);
        opponent = getArguments().getBoolean("opponent");
        rv = rootView.findViewById(R.id.HeroRecycler);
        globals = new Globals();

        TextView tv = new TextView(getActivity());
        tv.setText("Hero Page: "+opponent);

        LinearLayout ll = rootView.findViewById(R.id.hero_background);
        ll.addView(tv);

        (rootView.findViewById(R.id.hero_background)).setBackgroundColor(opponent?Color.RED:Color.BLUE);

        setUpView();

        return rootView;
    }

    public void setUpView()
    {

        models = globals.db.currentStatsDao().getHeroes(opponent?1:0);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        HeroAdaptor unitAdaptor = new HeroAdaptor(models);
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
