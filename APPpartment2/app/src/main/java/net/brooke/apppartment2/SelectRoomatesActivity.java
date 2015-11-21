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
        String aptCode = ParseUser.getCurrentUser().getString("household");

        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereEqualTo("household", aptCode);

        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {

                    ArrayList<String> names = new ArrayList<String>();

                    for (int i = 0; i < users.size(); i++) {
                        ParseUser user = users.get(i);

                        String name = user.getString("name");
                        names.add(name);
                    }

                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

                    listView.setAdapter(new ArrayAdapter<String>(SelectRoomatesActivity.this,
                            android.R.layout.simple_list_item_multiple_choice,
                            names));
                }
            }
        });
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select_roomates);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }
}
