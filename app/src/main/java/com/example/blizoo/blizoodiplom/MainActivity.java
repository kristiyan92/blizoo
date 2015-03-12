package com.example.blizoo.blizoodiplom;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.blizoo.blizoodiplom.adapters.NavDrawerListAdapter;
import com.example.blizoo.blizoodiplom.models.NavDrawerItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends DefaultActivity {

    private ActionBarDrawerToggle mDrawerToggle;

    //Navigation drawer title (if opened)
    private CharSequence mDrawerTitle;

    //Action bar title
    private CharSequence mTitle;

    //Navigation drawer menu item titles
    private String[] mMenuTitles;

    //Navigation drawer menu item icons
    private TypedArray mNavMenuIcons;

    //Navigation drawer menu item icons
    private TypedArray mNavMenuIconsSelected;

    //Navigation drawer menu items array list
    static ArrayList<NavDrawerItem> navDrawerItems;

    //Adapter of the navigation drawer
    private NavDrawerListAdapter adapter;

    //Array list of back stack value
    public static final ArrayList<Integer> backStack = new ArrayList<Integer>();

    // private SessionManager mSession;

    //Layout elements
    private DrawerLayout mDrawerLayout;
    private static ListView mDrawerList;
    private Toolbar mApplicationToolbar;
    private RelativeLayout mDrawer;
    private ImageButton mDrawerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayoutElements();

        setNavigationDrawer(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
//        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        // get last fragment which is on top of a stack
        try {
            backStack.remove(backStack.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getFragmentManager().popBackStack();
        if (backStack.size() == 0) {
            finish();
        }
        super.onBackPressed();

    }

    /*
     * Set the selected side menu item
     **/
    public void selectNavigationDrawer(int itemPosition) {

        String[] sideMenuElements = getResources().getStringArray(
                R.array.menu_items);
        mTitle = sideMenuElements[itemPosition];

        // Highlight the selected item, update the title, and close the drawer
        for (NavDrawerItem element : MainActivity.navDrawerItems) {
            element.setSelected(false);
            MainActivity.mDrawerList.setItemChecked(
                    MainActivity.navDrawerItems.indexOf(element), false);

            if (MainActivity.navDrawerItems.indexOf(element) == itemPosition) {
                element.setSelected(true);
                MainActivity.mDrawerList.setItemChecked(
                        MainActivity.navDrawerItems.indexOf(element), true);

            }
        }

    }

    /**
     * Prettify the given dates to more readable string*
     *
     * @param startDate - date for the first part of the pretty string
     * @param endDate   - date for the second part of the pretty string
     * @return - pretty data (Ex. "9:00 - 9:30")
     */
    public String getConferenceActivityTime(String startDate, String endDate) {

        try {
            String[] date1 = startDate.split(" ");
            String[] date2 = endDate.split(" ");

            String startTime = date1[1];
            String endTime = date2[1];

            return startTime + " - " + endTime;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Method which parse two strings to dates and check whatever current time is
     * between these two dates
     *
     * @param startDate
     * @param endData
     * @return true if current time is between dates
     */
    public boolean isInCurrentTime(String startDate, String endData) {
        // make calendar instance
        Calendar cal = Calendar.getInstance();
        // set date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        // get current time
        String currentTime = sdf.format(cal.getTime());

        try {
            // parse strings to dates
            Date current = (Date) sdf.parse(currentTime);
            Date givenStartTimeDate = (Date) sdf.parse(startDate);
            Date givenEndTimeDate = (Date) sdf.parse(endData);

            //int difference = current.compareTo(givenStartTimeDate);
            int compareWithStartData = current.compareTo(givenStartTimeDate);
            int compareWithEndData = current.compareTo(givenEndTimeDate);

           /* if (difference == 0) {
                // the dates are equal
            } else if (difference < 0) {
                // the date is before now
            } else {
                // the date is after now
            }*/

            // if current data is between start and end given dates
            // return true to expand this item
            if (compareWithStartData > 0 && compareWithEndData < 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unused")
    /**
     * Method which change date format to dd.MM.yyyy
     * @param date
     * @return - formatted date
     */
    public String changeDateFormat(String date) {
        // set date format
        SimpleDateFormat sdfOriginal = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat sdfNew = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date parseStrToDate = null;
        String formattedDate = "";
        try {
            // parse string to date, to change it format
            parseStrToDate = (Date) sdfOriginal.parse(date);
            // set formatted date
            formattedDate = sdfNew.format(parseStrToDate);
            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return formattedDate;
        }
    }

    /**
     * Initialize layout elements and set there click listeners
     */
    private void initializeLayoutElements() {

        mTitle = mDrawerTitle = getTitle();

        //initialize the session manager
//        mSession = new SessionManager(this);

        //nav drawer titles from resources
        mMenuTitles = getResources().getStringArray(R.array.menu_items);
        // nav drawer icons from resources
        mNavMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        //nav drawer selected icons from resources
        mNavMenuIconsSelected = getResources().obtainTypedArray(R.array.nav_drawer_icons_selected);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawer = (RelativeLayout) findViewById(R.id.drawer_frame);
        mDrawerButton = (ImageButton) findViewById(R.id.drawer_imagebutton);
        mDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        /**
         * Set the navigation drawer above the toolbar*
         * http://stackoverflow.com/a/26440880/3248003*
         * *
         * Helps you to create a custom navigation drawer layout*
         * http://stackoverflow.com/questions/25135166/add-image-above-item-list-in-navigation-drawer*
         */
        mApplicationToolbar = (Toolbar) findViewById(R.id.app_toolbar);
    }

    private void setNavigationDrawer(Bundle savedInstanceState) {

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.menu_list_item, mMenuTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        navDrawerItems = new ArrayList<NavDrawerItem>();

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //Program
        navDrawerItems.add(new NavDrawerItem(
                mMenuTitles[0],
                mNavMenuIcons.getResourceId(0, 0),
                mNavMenuIconsSelected.getResourceId(0, 0),
                true));
        // Lectures
        navDrawerItems.add(new NavDrawerItem(
                mMenuTitles[1],
                mNavMenuIcons.getResourceId(1, 0),
                mNavMenuIconsSelected.getResourceId(1, 0),
                false));
        // Partners
        navDrawerItems.add(new NavDrawerItem(
                mMenuTitles[2],
                mNavMenuIcons.getResourceId(2, 0),
                mNavMenuIconsSelected.getResourceId(2, 0),
                false));
        // Sponsors
        navDrawerItems.add(new NavDrawerItem(
                mMenuTitles[3],
                mNavMenuIcons.getResourceId(3, 0),
                mNavMenuIconsSelected.getResourceId(3, 0),
                false));
        // How to get there
        navDrawerItems.add(new NavDrawerItem(
                mMenuTitles[4],
                mNavMenuIcons.getResourceId(4, 0),
                mNavMenuIconsSelected.getResourceId(4, 0),
                false));
/*
        if (mSession.getApplicationLanguage().equals("bg")) {
            // How to get there
            navDrawerItems.add(new NavDrawerItem(
                    mMenuTitles[5],
                    mNavMenuIcons.getResourceId(6, 0),
                    mNavMenuIconsSelected.getResourceId(6, 0),
                    false));
        } else {
            // How to get there
            navDrawerItems.add(new NavDrawerItem(
                    mMenuTitles[5],
                    mNavMenuIcons.getResourceId(5, 0),
                    mNavMenuIconsSelected.getResourceId(5, 0),
                    false));
        }*/


        mNavMenuIcons.recycle();
        mNavMenuIconsSelected.recycle();

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);


        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mApplicationToolbar,
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mDrawerLayout.bringChildToFront(mDrawer);
                mDrawerLayout.requestLayout();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    /**
     * The click listner for ListView in the navigation drawer
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            for (NavDrawerItem element : navDrawerItems) {
                element.setSelected(false);
                if (navDrawerItems.indexOf(element) == position) {

                    element.setSelected(true);

                }
            }
        }
    }


    /**
     * Swaps fragments in the main content view.With help of backStack array
     * we remember the position and order to already opened element in side menu.
     * When we got the order in onBackPress method close them in the same order
     */
    private void selectItem(int position) {

        //Set selected menu item if the item is different from the language tab
        if (position != 5) {
            mDrawerList.setItemChecked(position, true);
            setTitle(mMenuTitles[position]);
        }

        // update the main content by replacing fragments

        FragmentManager mFragmentManager;
        FragmentTransaction mFragmentTransaction;
        // Initialize fragment manager and transaction
        mFragmentManager = getSupportFragmentManager();
        // Insert the fragment by replacing any existing fragment
        mFragmentTransaction = mFragmentManager.beginTransaction();

        // replace fragments dependence which item is clicked
        switch (mDrawerList.getCheckedItemPosition()) {

            case 0:
                backStack.add(0);
                CoveringFragment lecturersFragment = new CoveringFragment();
                mFragmentTransaction
                        .replace(R.id.content_frame, lecturersFragment, "LecturersFragment")
                        .addToBackStack("LecturersFragment").commit();
                break;
         /*   case 1:
                backStack.add(1);
                LecturersFragment lecturersFragment = new LecturersFragment();
                mFragmentTransaction
                        .replace(R.id.content_frame, lecturersFragment, "LecturersFragment")
                        .addToBackStack("LecturersFragment").commit();
                break;
            case 2:
                backStack.add(2);
                PartnersFragment partnersFragment = new PartnersFragment();
                mFragmentTransaction
                        .replace(R.id.content_frame, partnersFragment, "PartnersFragment")
                        .addToBackStack("PartnersFragment").commit();
                break;
            case 3:
                backStack.add(3);
                SponsorsFragment sponsorsFragment = new SponsorsFragment();
                mFragmentTransaction
                        .replace(R.id.content_frame, sponsorsFragment, "SponsorsFragment")
                        .addToBackStack("SponsorsFragment").commit();
                break;
            case 4:
                backStack.add(4);
                CongressDestinationFragment congressDestinationFragment = new CongressDestinationFragment();
                mFragmentTransaction
                        .replace(R.id.content_frame, congressDestinationFragment, "CongressDestinationFragment")
                        .addToBackStack("CongressDestinationFragment").commit();
                break;*/
          /*  case 5:

                if (mSession.getApplicationLanguage().equals("bg")) {

                    mSession.setApplicationLanguage("en");

                } else {

                    mSession.setApplicationLanguage("bg");

                }

                setApplicationLanguage();

                break;*/

        }

        // close side menu
        mDrawerLayout.closeDrawer(mDrawer);
    }


}