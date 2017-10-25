package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

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
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        Globals globals = new Globals();
        globals.setUpDb(getApplicationContext());

//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "model_stats").allowMainThreadQueries().build();

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

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        SlidingUpPanelLayout s = findViewById(R.id.sliding_layout);
        s.setAnchorPoint(0.7f);
        s.setPanelHeight(60);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.frame_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

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
            Bundle args = new Bundle();

            switch (position){

                case 0:
                    fragment = new UnitStatsFragment();
                    args.putBoolean("opponent", false);
                    fragment.setArguments(args);
                    currentPage = 0;
                    break;
                case 1:
                    fragment = new HeroStatsFragment();
                    args.putBoolean("opponent", false);
                    fragment.setArguments(args);
                    currentPage = 1;
                    break;
                case 2:
                    fragment = new HeroStatsFragment();
                    args.putBoolean("opponent", true);
                    fragment.setArguments(args);
                    currentPage = 2;
                    break;
                default:
                    fragment = new UnitStatsFragment();
                    args.putBoolean("opponent", true);
                    fragment.setArguments(args);
                    currentPage = 3;
                    break;
            }

            return fragment;

        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    public void setmViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    public void startSearch(View view){

        // TODO: 25/10/2017 Pass the current page: currentPage


        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        args.putInt("page", currentPage);
        fragment.setArguments(args);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.frame_container,  fragment);
        ft.commit();
    }

}
