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

        try {
            updateTotalAmount();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParseUser user = ParseUser.getCurrentUser();
        String aptCode = user.getString("household");

        //System.out.println(aptCode);


        ParseQuery<ParseUser> usersQuery = ParseUser.getQuery();
        usersQuery.whereEqualTo("household", aptCode);

        usersQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> liverList, ParseException e) {
                if (e == null) {

                    int size = liverList.size();

                    if (size > 0) {
                        TextView myTextView1 = (TextView) findViewById(R.id.Person1);
                        myTextView1.setText(liverList.get(0).getString("name"));



                        TextView myBillView1 = (TextView) findViewById(R.id.Bill1);
                        myBillView1.setText(liverList.get(0).get("totalAmount").toString());

                    }

                    if (size > 1) {
                        TextView myTextView2 = (TextView) findViewById(R.id.Person2);
                        myTextView2.setText(liverList.get(1).getString("name"));

                        TextView myBillView2 = (TextView) findViewById(R.id.Bill2);
                        myBillView2.setText(liverList.get(1).get("totalAmount").toString());
                    }

                    if (size > 2) {
                        TextView myTextView3 = (TextView) findViewById(R.id.Person3);
                        myTextView3.setText(liverList.get(2).getString("name"));

                        TextView myBillView3 = (TextView) findViewById(R.id.Bill3);
                        myBillView3.setText(liverList.get(2).get("totalAmount").toString());
                    }

                    if (size > 3) {
                        TextView myTextView4 = (TextView) findViewById(R.id.Person4);
                        myTextView4.setText(liverList.get(3).getString("name"));

                        TextView myBillView4 = (TextView) findViewById(R.id.Bill4);
                        myBillView4.setText(liverList.get(3).get("totalAmount").toString());
                    }

                    if (size > 4) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person5);
                        myTextView5.setText(liverList.get(4).getString("name"));

                        TextView myBillView5 = (TextView) findViewById(R.id.Bill5);
                        myBillView5.setText(liverList.get(4).get("totalAmount").toString());
                    }

                    if (size > 5) {
                        TextView myTextView6 = (TextView) findViewById(R.id.Person6);
                        myTextView6.setText(liverList.get(5).getString("name"));

                        TextView myBillView6 = (TextView) findViewById(R.id.Bill6);
                        myBillView6.setText(liverList.get(5).get("totalAmount").toString());
                    }

                    if (size > 6) {
                        TextView myTextView7 = (TextView) findViewById(R.id.Person7);
                        myTextView7.setText(liverList.get(6).getString("name"));

                        TextView myBillView7 = (TextView) findViewById(R.id.Bill7);
                        myBillView7.setText(liverList.get(6).get("totalAmount").toString());
                    }

                    if (size > 7) {
                        TextView myTextView8 = (TextView) findViewById(R.id.Person8);
                        myTextView8.setText(liverList.get(7).getString("name"));

                        TextView myBillView8 = (TextView) findViewById(R.id.Bill8);
                        myBillView8.setText(liverList.get(7).get("totalAmount").toString());
                    }

                    if (size > 8) {
                        TextView myTextView9 = (TextView) findViewById(R.id.Person9);
                        myTextView9.setText(liverList.get(8).getString("name"));

                        TextView myBillView9 = (TextView) findViewById(R.id.Bill9);
                        myBillView9.setText(liverList.get(8).get("totalAmount").toString());
                    }

                    if (size > 9) {
                        TextView myTextView10 = (TextView) findViewById(R.id.Person10);
                        myTextView10.setText(liverList.get(9).getString("name"));

                        TextView myBillView10 = (TextView) findViewById(R.id.Bill10);
                        myBillView10.setText(liverList.get(9).get("totalAmount").toString());
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

    public void updateTotalAmount() throws ParseException {



        ParseQuery<ParseObject> bills = ParseQuery.getQuery("Bills");
        bills.whereEqualTo("Household", ParseUser.getCurrentUser().getString("household"));
        //System.out.println(ParseUser.getCurrentUser().getString("household"));

        //final Integer totalAmount;

        final ParseQuery<ParseUser> users = ParseQuery.getQuery("User");
        users.whereEqualTo("household", ParseUser.getCurrentUser().getString("household"));
        users.whereEqualTo("liverNum", 1);


        final ParseObject correctUser = users.getFirst();


        bills.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> column, ParseException e) {
                // check for ParseException
                String inhabitant = "inhabitant1";
                Integer sum = 0;
                for (final ParseObject billValue : column) {
                    sum += (Integer) billValue.get(inhabitant);
                    //System.out.println(sum);
                }
                // there is your SUM
                System.out.println("Total: " + sum);
                Integer totalAmount = sum;
                String liverNum = inhabitant.replace("inhabitant", "");
                final Integer liverInt = Integer.parseInt(liverNum);

                //users.whereEqualTo("liverNum", liverInt);

                //try {
                    //ParseObject correctUser = users.getFirst();
                    correctUser.put("totalAmount", totalAmount);
                    correctUser.saveInBackground();
               // } catch (ParseException e1) {
                 //   e1.printStackTrace();
               // }
            }











                /*
                users.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        System.out.println(totalAmount);
                        ParseObject object = objects.get(0);
                        object.put("totalAmount", totalAmount);
                        object.saveInBackground();
                    }
                }); */
            });
        } //);

    //}

}


