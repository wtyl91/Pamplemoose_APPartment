package net.brooke.apppartment2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelectRoomatesActivity extends AppCompatActivity {

    private ListView listView = null;
    private ArrayList<String> names;

    public void onClickSelectAll(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        if(checked){
            for(int i = 0; i< listView.getAdapter().getCount(); i++){
                listView.setItemChecked(i, true);
            }
        } else {
            for(int i = 0; i< listView.getAdapter().getCount(); i++){
                listView.setItemChecked(i, false);
            }
        }

    }

    public void onClickToEven(View v) {

        listView.getCheckedItemCount();
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        int siz = checkedItems.size();
        int[] listOfNums = new int[siz];

        int num = 0;
        int offset = 0;
        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i) ) {
                    String item = listView.getAdapter().getItem(
                            checkedItems.keyAt(i)).toString();
                    //System.out.println("keyAt: " + checkedItems.keyAt(i));
                    if (checkedItems.keyAt(i) == ParseUser.getCurrentUser().getInt("liverNum")) {
                        offset = 1;
                    }

                    //if (checkedItems.keyAt(i))
                    num = checkedItems.keyAt(i) + 1 + offset;
                        listOfNums[i] = checkedItems.keyAt(i) + 1 + offset;
                        System.out.println("Hi" + listOfNums[i]);
                        Log.i("Hello", item + " was selected");


                }
            }
            System.out.println(Arrays.toString(listOfNums));
        }

        if(listOfNums.length > 0) {
            Intent intent = new Intent(SelectRoomatesActivity.this, AddBillEvenlyActivity.class);
            intent.putExtra("numList", listOfNums);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Need to select at least 1 person.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String aptCode = ParseUser.getCurrentUser().getString("household");
        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.whereEqualTo("household", aptCode);

        // Remove Current User from Query
        userQuery.whereNotEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    // Sort users list by liverNum order
                    Collections.sort(users, new Comparator<ParseUser>() {
                        public int compare(ParseUser u1, ParseUser u2) {
                            return u1.getInt("liverNum") - u2.getInt("liverNum");
                        }
                    });

                    names = new ArrayList<String>();

                    for (int i = 0; i < users.size(); i++) {
                        ParseUser user = users.get(i);

                        String name = user.getString("name");
                        names.add(name);
                    }

                    listView = (ListView) findViewById(R.id.list);
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
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.appartment_logo_red);

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRoomatesActivity.this, Dashboard_w_NavDrawer.class);
                startActivity(intent);
            }
        });

    }
}
