package net.brooke.apppartment2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class SelectRoomatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ParseUser user = ParseUser.getCurrentUser();
        String aptCode = user.getString("household");

        ParseQuery<ParseUser> usersQuery = ParseUser.getQuery();
        usersQuery.whereEqualTo("household", aptCode);

        usersQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> liverList, ParseException e) {
                if (e == null) {

                    int size = liverList.size();

                    if (size > 0) {
                        TextView myTextView1 = (TextView) findViewById(R.id.Person1);
                        myTextView1.setText(liverList.get(0).getString("name"));
                    }

                    if (size > 1) {
                        TextView myTextView2 = (TextView) findViewById(R.id.Person2);
                        myTextView2.setText(liverList.get(1).getString("name"));
                    }

                    if (size > 2) {
                        TextView myTextView3 = (TextView) findViewById(R.id.Person3);
                        myTextView3.setText(liverList.get(2).getString("name"));
                    }

                    if (size > 3) {
                        TextView myTextView4 = (TextView) findViewById(R.id.Person4);
                        myTextView4.setText(liverList.get(3).getString("name"));
                    }

                    if (size > 4) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person5);
                        myTextView5.setText(liverList.get(4).getString("name"));
                    }

                    if (size > 5) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person6);
                        myTextView5.setText(liverList.get(5).getString("name"));
                    }

                    if (size > 6) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person7);
                        myTextView5.setText(liverList.get(6).getString("name"));
                    }

                    if (size > 7) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person8);
                        myTextView5.setText(liverList.get(7).getString("name"));
                    }

                    if (size > 8) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person9);
                        myTextView5.setText(liverList.get(8).getString("name"));
                    }

                    if (size > 9) {
                        TextView myTextView5 = (TextView) findViewById(R.id.Person10);
                        myTextView5.setText(liverList.get(9).getString("name"));
                    }


                } else {
                    Log.d("roommates", "Error: " + e.getMessage());
                }
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_roomates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
