package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.arch.persistence.room.Room;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.facebook.stetho.Stetho;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "model_stats").allowMainThreadQueries().build();

//        Faction[] factions = {new Faction(1, "Minas Tirith"), new Faction(2, "Mordor")};
//        db.factionDAO().insertAll(factions);
//
//        Model[] models = {new Model(1, "Aragorn, King Elessar", 6, 3, 4, 7, 3, 3, 6, 3, 3, 3, true),
//                new Model(2, "The Dark Lord Sauron", 9, 4 , 8, 10, 3, 3, 7, 3, 6, 0, true),
//                new Model(3, "Warrior Of Minas Tirith", 3, 4, 3, 5, 1, 1, 3, 0, 0, 0, false),
//                new Model(4, "Orc Warrior", 3, 5, 3, 4, 1, 1, 2, 0, 0, 0, false)};
//        db.modelDao().insertAll(models);
//
//        FactionsHaveModels[] factionsHaveModels = {new FactionsHaveModels(1, 1, 1), new FactionsHaveModels(2, 2, 2),
//                new FactionsHaveModels(3, 1, 3), new FactionsHaveModels(4, 2, 4)};
//        db.factionHasModelsDao().insertAll(factionsHaveModels);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position){

                case 0:
                    fragment = new UnitStatsFragment();
                    break;
                case 1:
                    fragment = new HeroStatsFragment();
                    break;
                case 2:
                    fragment = new HeroStatsFragment();
                    break;
                default:
                    fragment = new UnitStatsFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
