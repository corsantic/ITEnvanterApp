package com.example.hsarkisla.itenvantermobilapp.Activity;
/**Furkan Aydın-Intern in Tuvasas*/

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hsarkisla.itenvantermobilapp.Fragment.UrunAraFragment;
import com.example.hsarkisla.itenvantermobilapp.Fragment.UrunTeslimFragment;
import com.example.hsarkisla.itenvantermobilapp.Fragment.YeniUrunFragment;
import com.example.hsarkisla.itenvantermobilapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int navItemIndex = 0;
    private NavigationView navigationView;
    private static final String TAG_URUN_ARA = "Ürün Ara";
    private static final String TAG_URUN_TESLİM = "Ürün Teslim ";
    private static final String TAG_YENİ_URUN = "Yeni Ürün";
    private Handler mHandler;
    private DrawerLayout drawer;
    private View navHeader;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private String[] activityTitles;
    private Toolbar toolbar;

    public static String yeniUrunBarcode = "";


    public static String CURRENT_TAG = TAG_URUN_ARA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        //imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_URUN_ARA;
            loadHomeFragment();
        }
        if (getIntent().getExtras() != null) {
            yeniUrunBarcode = getIntent().getExtras().getString("BARCODE");

                if (!yeniUrunBarcode.equals("")) {
                    {
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_YENİ_URUN;
                        loadHomeFragment();
                    }

                }
            }
        }



    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_urun_ara:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_URUN_ARA;
                        break;
                    case R.id.nav_urun_teslim:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_URUN_TESLİM;
                        break;
                    case R.id.nav_yeni_urun:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_YENİ_URUN;
                        break;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments

                Fragment fragment = getHomeFragment();

                if (!yeniUrunBarcode.equals("")) // barcode var ise onunla birlikte fragment'i baslatsin
                {
                    Bundle args = new Bundle();
                    args.putString("BARCODE", yeniUrunBarcode);
                    fragment.setArguments(args);
                }

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // urun ara
                UrunAraFragment urunAraFragment = new UrunAraFragment();
                return urunAraFragment;
            case 1:
                // urun teslim
                UrunTeslimFragment urunTeslim = new UrunTeslimFragment();

                return urunTeslim;
            case 2:
                //  yeni urun
                YeniUrunFragment yeniUrun = new YeniUrunFragment();
                return yeniUrun;


            default:
                return new UrunTeslimFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_URUN_ARA;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//todo ekleme yapılabilir
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 5) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//todo ekleme yapılabilir
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
