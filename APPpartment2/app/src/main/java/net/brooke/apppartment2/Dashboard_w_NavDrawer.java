package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;


public class Dashboard_w_NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Retrieves the current user's apartment code string
        ParseUser user = ParseUser.getCurrentUser();
        String aptCode = user.getString("household");

        // Retrieves the household associated with the current user's apartment
        ParseQuery<ParseObject> household = ParseQuery.getQuery("Household");
        household.whereEqualTo("houseID", aptCode);

        household.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    System.out.println("Error: Could not find apartment.");
                } else {
                    // Gets the inhabitants array from Household object
                    ArrayList<String> inhabitants;
                    inhabitants = (ArrayList<String>) object.get("inhabitants");

                    int size = inhabitants.size();

                    // Prints out inhabitants to the TextViews
                    if (size > 0) {
                        TextView myTextView1 = (TextView) findViewById(R.id.Person1);
                        myTextView1.setText(inhabitants.get(0));
                    }

                    if (size > 1) {
                        TextView myTextView2 = (TextView) findViewById(R.id.Person2);
                        myTextView2.setText(inhabitants.get(1));
                    }

                    if (size > 2) {
                        TextView myTextView3 = (TextView) findViewById(R.id.Person3);
                        myTextView3.setText(inhabitants.get(2));
                    }

                    if (size > 3) {
                        TextView myTextView4 = (TextView) findViewById(R.id.Person4);
                        myTextView4.setText(inhabitants.get(3));
                    }

                    if (size > 4) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person5);
                        myTextView5.setText(inhabitants.get(4));
                    }
                }
            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_w__nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_w__nav_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_bill) {
            // Handle the camera action
            Intent intent = new Intent(Dashboard_w_NavDrawer.this,AddBillActivity.class);
            startActivity(intent);
        } else if (id == R.id.add_roommate) {
            Intent intent = new Intent(Dashboard_w_NavDrawer.this, AddRoommateActivity.class);
            startActivity(intent);
        } else if (id == R.id.bill_summary) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
