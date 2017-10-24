package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by matthew.weilding on 25/10/2017.
 */

public class SearchFragment extends Fragment {

    RecyclerView rv;
    Globals globals;
    boolean faction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        rv = rootView.findViewById(R.id.searchRecycler);
        globals = new Globals();
        globals.setUpDb(getActivity());

        return rootView;

    }

    void setUpSearch(){

        if(faction)
        {
            boolean orientation = (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
            StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(
                    orientation?4:2, StaggeredGridLayoutManager.VERTICAL);
            rv.setLayoutManager(mStaggeredLayoutManager);
            FactionAdaptor factionAdaptor = new FactionAdaptor();
            rv.setAdapter(factionAdaptor);
        }
        else
        {
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
        }


        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };

        rv.setItemAnimator(animator);



    }

}
