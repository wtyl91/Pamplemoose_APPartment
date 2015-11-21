package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomatesActivity extends AppCompatActivity {

    public void onClickToEven(View v) {
        Intent intent = new Intent(SelectRoomatesActivity.this, AddBillEvenlyActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ParseQuery<ParseObject> apartmentsQuery = ParseQuery.getQuery("Household");
        apartmentsQuery.whereEqualTo("houseID", ParseUser.getCurrentUser().getString("household"));

        apartmentsQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> apartments, ParseException e) {
                if (e == null) {
                    ArrayList<String> inhabitants = (ArrayList) apartments.get(0).getList("inhabitants");

                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

                    listView.setAdapter(new ArrayAdapter<String>(SelectRoomatesActivity.this,
                            android.R.layout.simple_list_item_multiple_choice,
                            inhabitants));
                } else {
                    Log.d("apartments", "Error: " + e.getMessage());
                }
            }
        });

        /*
        ParseUser user = ParseUser.getCurrentUser();
        String aptCode = user.getString("household");

        ParseQuery<ParseUser> usersQuery = ParseUser.getQuery();
        usersQuery.whereEqualTo("household", aptCode);

        usersQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> liverList, ParseException e) {
                if (e == null) {

                    int size = liverList.size();

                    if (size > 0) {
                        CheckBox myCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
                        myCheckBox1.setText(liverList.get(0).getString("name"));
                    }

                    if (size > 1) {
                        CheckBox myCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
                        myCheckBox2.setText(liverList.get(1).getString("name"));
                    }

                    if (size > 2) {
                        CheckBox myCheckBox3 = (CheckBox) findViewById(R.id.checkBox3);
                        myCheckBox3.setText(liverList.get(2).getString("name"));
                    }

                    if (size > 3) {
                        CheckBox myCheckBox4 = (CheckBox) findViewById(R.id.checkBox4);
                        myCheckBox4.setText(liverList.get(3).getString("name"));
                    }

                    if (size > 4) {
                        CheckBox myCheckBox5 = (CheckBox) findViewById(R.id.checkBox5);
                        myCheckBox5.setText(liverList.get(4).getString("name"));
                    }

                    if (size > 5) {
                        CheckBox myCheckBox6 = (CheckBox) findViewById(R.id.checkBox6);
                        myCheckBox6.setText(liverList.get(5).getString("name"));
                    }

                    if (size > 6) {
                        CheckBox myCheckBox7 = (CheckBox) findViewById(R.id.checkBox7);
                        myCheckBox7.setText(liverList.get(6).getString("name"));
                    }

                    if (size > 7) {
                        CheckBox myCheckBox8 = (CheckBox) findViewById(R.id.checkBox8);
                        myCheckBox8.setText(liverList.get(7).getString("name"));
                    }

                    if (size > 8) {
                        CheckBox myCheckBox9 = (CheckBox) findViewById(R.id.checkBox9);
                        myCheckBox9.setText(liverList.get(8).getString("name"));
                    }

                    if (size > 9) {
                        CheckBox myCheckBox10 = (CheckBox) findViewById(R.id.checkBox10);
                        myCheckBox10.setText(liverList.get(9).getString("name"));
                    }


                } else {
                    Log.d("roommates", "Error: " + e.getMessage());
                }
            }
        }); */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_roomates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
