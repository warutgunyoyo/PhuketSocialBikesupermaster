package com.socialbike.phuketsocialbike;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.NavDrawerListAdapter;
import bean.NavDrawerItem;


public class Trip_IntroduceActivity extends ActionBarActivity {

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip__introduce);
        slide_manu();


        //BTN create_trip_btn
        Button create_trip_btn = (Button) findViewById(R.id.create_trip_btn);
        create_trip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip_IntroduceActivity.this,CreateTripActivity.class);
                startActivity(contractBtn);
            }
        });

        //BTN Weather
        Button weather1 = (Button) findViewById(R.id.weather_btn);
        weather1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip_IntroduceActivity.this,WeatherActivity.class);
                startActivity(contractBtn);
            }
        });
        //BTN Emergency
        Button emergency = (Button) findViewById(R.id.emergency_btn);
        emergency.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip_IntroduceActivity.this,MapsActivity.class);
                startActivity(contractBtn);
            }
        });

        Button tr1 = (Button) findViewById(R.id.trip1);
        tr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip_IntroduceActivity.this,Trip1.class);
                startActivity(contractBtn);
            }
        });
        Button tr2 = (Button) findViewById(R.id.trip2);
        tr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip_IntroduceActivity.this,Trip2.class);
                startActivity(contractBtn);
            }
        });
        Button tr3 = (Button) findViewById(R.id.trip3);
        tr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contractBtn = new Intent(Trip_IntroduceActivity.this,Trip3.class);
                startActivity(contractBtn);
            }
        });

    }

    private void slide_manu() {
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        //listView = (ListView) findViewById(R.id.listView);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Profile
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Hospital
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Officer
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // US
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Setting
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Logout
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

    }

    /**Slide manu **/
    private class SlideMenuClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // display view for selected nav drawer item
            displayView(position);
        }

        private void displayView(int position) {
            // update the main content by replacing fragments
            Fragment fragment = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trip__introduce, menu);
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
}