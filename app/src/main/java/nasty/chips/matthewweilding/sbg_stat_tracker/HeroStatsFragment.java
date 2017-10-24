package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by matthew.weilding on 24/10/2017.
 */

public class HeroStatsFragment extends Fragment {

    boolean opponent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hero_stats, container, false);
        opponent = getArguments().getBoolean("opponent");

        TextView tv = new TextView(getActivity());
        tv.setText("Hero Page: "+opponent);

        LinearLayout ll = rootView.findViewById(R.id.hero_background);
        ll.addView(tv);

        (rootView.findViewById(R.id.hero_background)).setBackgroundColor(opponent?Color.RED:Color.BLUE);

        return rootView;
    }
}
