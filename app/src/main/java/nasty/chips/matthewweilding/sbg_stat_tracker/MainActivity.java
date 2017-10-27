package nasty.chips.matthewweilding.sbg_stat_tracker;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.stetho.Stetho;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import nasty.chips.matthewweilding.sbg_stat_tracker.Database.DatabaseSetup.AppDatabase;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Faction;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.FactionsHaveModels;
import nasty.chips.matthewweilding.sbg_stat_tracker.Database.Model;

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
    public Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        globals = new Globals();
        globals.setUpDb(getApplicationContext());

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "model_stats").allowMainThreadQueries().build();

        Faction[] factions = {new Faction(1, "Minas Tirith"), new Faction(2, "Mordor"), new Faction(3,"Wanderers In The Wild"),
                new Faction(4,"The Survivors Of Laketown"), new Faction(5,"Isengard"), new Faction(6, "Hobbits Of The Shire"),
                new Faction(7, "The Kingdom Of Rohan"), new Faction(8, "The Fiefdoms")};
        db.factionDAO().insertAll(factions);

        Model[] models = {new Model(1, "Aragorn, King Elessar", 6, 6, 3, 4, 7, 3, 3, 6, 3, 3, 3, true),
                new Model(2, "The Dark Lord Sauron", 6,9, 4 , 8, 10, 3, 3, 7, 3, 6, 0, true),
                new Model(3, "Warrior Of Minas Tirith", 6,3, 4, 3, 5, 1, 1, 3, 0, 0, 0, false),
                new Model(4, "Orc Warrior", 6,3, 5, 3, 4, 1, 1, 2, 0, 0, 0, false),
                new Model(5, "Knight Of Minas Tirith", 6, 3, 4,3,5,1,1,3,0,0,0,false),
                new Model(6, "Ranger Of Gondor",6,3,3,3,4,1,1,3,0,0,0,false),
                new Model(7, "Cave Troll", 6, 6, 5, 6,6,3,3,3,0,0,0,false)};
        db.modelDao().insertAll(models);

        FactionsHaveModels[] factionsHaveModels = {new FactionsHaveModels(1, 1, 1), new FactionsHaveModels(2, 2, 2),
                new FactionsHaveModels(3, 1, 3), new FactionsHaveModels(4, 2, 4),
                new FactionsHaveModels(5,1,5), new FactionsHaveModels(6, 1,6),
                new FactionsHaveModels(7,2,7)};
        db.factionHasModelsDao().insertAll(factionsHaveModels);

        SlidingUpPanelLayout s = findViewById(R.id.sliding_layout);
        s.setAnchorPoint(0.7f);
        s.setPanelHeight(60);


        setUpViewPager();

    }

    void setUpViewPager(){

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.frame_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void clearSide(View view) {

        new MaterialStyledDialog.Builder(this)
                .setTitle("Remove Current Models")
                .setDescription("Are you sure you want to delete these stats?")
                .setPositiveText("Remove")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    deleteItems();

                                }
                })
                .setNegativeText("Cancel")
                .show();

    }

    public void deleteItems(){

        Log.e("Current Page", "Current Page: "+mViewPager.getCurrentItem());

        mViewPager.getCurrentItem();

        if (mViewPager.getCurrentItem() <= 1)
        {
            globals.db.currentStatsDao().deleteSide(0);
        }
        else if (mViewPager.getCurrentItem() >= 2)
        {
            globals.db.currentStatsDao().deleteSide(1);
        }

        setUpViewPager();

    }

    public void loadList(View view) {

        new MaterialStyledDialog.Builder(this)
                .setTitle("Load List")
                .setDescription("Choose A list To Load")
                .setPositiveText("Remove")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .setNegativeText("Cancel")
                .show();


    }

    public void saveList(View view) {
        
        new MaterialStyledDialog.Builder(this)
                .setTitle("Save List")
                .setDescription("Are you sure you want to delete these stats?")
                .setPositiveText("Remove")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .setNegativeText("Cancel")
                .show();

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

            currentPage = position;

            switch (position){

                case 0:
                    fragment = new UnitStatsFragment();
                    args.putBoolean("opponent", false);
                    fragment.setArguments(args);
                    break;
                case 1:
                    fragment = new HeroStatsFragment();
                    args.putBoolean("opponent", false);
                    fragment.setArguments(args);
                    break;
                case 2:
                    fragment = new HeroStatsFragment();
                    args.putBoolean("opponent", true);
                    fragment.setArguments(args);
                    break;
                default:
                    fragment = new UnitStatsFragment();
                    args.putBoolean("opponent", true);
                    fragment.setArguments(args);
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

        Intent intent = new Intent(this, SearchActivity.class);
        Bundle b = new Bundle();
        b.putInt("page", mViewPager.getCurrentItem());
        Log.e("PageLog", "page: "+mViewPager.getCurrentItem());
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }

}
