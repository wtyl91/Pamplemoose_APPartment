package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class Dashboard_w_NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Retrieves the current user's apartment code string
/*
        try {
            updateTotalAmount();
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
        ParseUser user = ParseUser.getCurrentUser();
        final String aptCode = user.getString("household");

        //System.out.println(aptCode);


        ParseQuery<ParseUser> usersQuery = ParseUser.getQuery();
        usersQuery.whereEqualTo("household", aptCode);

        usersQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> liverList, ParseException e) {
                if (e == null) {

                    int size = liverList.size();

                    if (size > 0) {
                        ParseObject liver = liverList.get(0);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView1 = (TextView) findViewById(R.id.Person1);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView1);
                    }

                    if (size > 1) {
                        ParseObject liver = liverList.get(1);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person2);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);
                    }

                    if (size > 2) {
                        ParseObject liver = liverList.get(2);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person3);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

                    }

                    if (size > 3) {
                        ParseObject liver = liverList.get(3);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person4);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);
                    }

                    if (size > 4) {
                        ParseObject liver = liverList.get(4);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person5);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);
                    }

                    if (size > 5) {
                        ParseObject liver = liverList.get(5);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person6);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);
                    }

                    if (size > 6) {
                        ParseObject liver = liverList.get(6);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person6);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

                    }

                    if (size > 7) {
                        ParseObject liver = liverList.get(7);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person6);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

                    }

                    if (size > 8) {
                        ParseObject liver = liverList.get(8);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person6);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

                    }

                    if (size > 9) {
                        ParseObject liver = liverList.get(9);
                        int tagNum = (int)liver.getNumber("liverNum");
                        TextView myTextView = (TextView) findViewById(R.id.Person6);

                        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

                    }


                } else {
                    Log.d("roommates", "Error: " + e.getMessage());
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
    private void getAndDisplayNameSum(String aptCode, final ParseObject liver, final int liverNum, final TextView view){

        ParseQuery<ParseObject> billAmountQuery = ParseQuery.getQuery("Bills");
        billAmountQuery.whereEqualTo("Household", aptCode);

        billAmountQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> billList, ParseException e) {
                if (e == null) {
                    String oweCol = "inhabitant" + Integer.toString(liverNum);
                    Log.d("inhabitantQ", oweCol);
                    int sum = 0;

                    for (int i = 0; i < billList.size(); i++) {

                        sum += (int) billList.get(i).getNumber(oweCol);

                    }


                    view.setText(liver.getString("name") + " " + sum);

                }
            }
        });

    }

}


