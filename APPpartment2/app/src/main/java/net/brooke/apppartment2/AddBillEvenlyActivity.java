package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddBillEvenlyActivity extends AppCompatActivity {
    Bundle extras;
    int[] numList;

    public void evenlyAddBill(View v) {

        // Check if switch is on or off

        // TODO:
        // If Switch is Off, add bill using even split
        // If Switch is On, add bill using custom split
        final ListView listView = (ListView) findViewById(R.id.listView);

        // Even Split
        int countLiver=1;

        EditText totalAmount = (EditText) findViewById(R.id.editText5);
        EditText description = (EditText) findViewById(R.id.editText6);
        EditText title = (EditText) findViewById(R.id.editText7);

        final String code = totalAmount.getText().toString();
        final String titleStr = title.getText().toString();
        final String descriptionStr = description.getText().toString();

        float amount = Float.parseFloat(code);
        System.out.println("Total Amount: " + amount);

        // Assume checkboxes returned an array of liverNums called numList
        ParseObject newbill = new ParseObject("Bills");
        newbill.put("Creator", ParseUser.getCurrentUser().getString("username"));
        newbill.put("TotalAmount", amount);
        newbill.put("Description", descriptionStr);
        newbill.put("BillName", titleStr);
        newbill.put("Household", ParseUser.getCurrentUser().getString("household"));

        // Even Split
        int splitCount = numList.length;
        float splitAmount = amount / splitCount;
        while(countLiver<=10){
            String tagName = "inhabitant"+ Integer.toString(countLiver);
            newbill.put(tagName, 0);
            countLiver++;
        }

        for(Number num : numList){

            String tagName = "inhabitant"+ num.toString();

            if (num == ParseUser.getCurrentUser().getNumber("liverNum")) {
                newbill.put(tagName, (amount - splitAmount) * (-1));
            } else {
                newbill.put(tagName, splitAmount);
            }
            countLiver++;

        }

        newbill.saveInBackground();

        Intent intent = new Intent(AddBillEvenlyActivity.this,Dashboard_w_NavDrawer.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Successfully added bill.", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_evenly);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.appartment_logo_red);

        extras = getIntent().getExtras();
        numList = extras.getIntArray("numList");

        for (int i = 0; i < numList.length; i++) {
            System.out.println("numList: " + numList[i]);
        }

        setupListView();
    }

    private void setupListView() {

        final ListView listView = (ListView) findViewById(R.id.listView);

        String aptCode = ParseUser.getCurrentUser().getString("household");

        // Make a list of queries
        List<ParseQuery<ParseUser>> queries = new ArrayList<ParseQuery<ParseUser>>();


        for (int i = 0; i < numList.length; i++) {
            ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
            userQuery.whereEqualTo("household", aptCode);
            userQuery.whereEqualTo("liverNum", numList[i]);

            queries.add(userQuery);
        }
        ParseQuery<ParseUser> mainQuery = ParseQuery.or(queries);

        mainQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                String[] names = new String[numList.length];

                for (int i = 0; i < names.length; i++) {
                    names[i] = users.get(i).getString("name");
                }

                ArrayAdapter<String> aa=new ArrayAdapter<String>(AddBillEvenlyActivity.this, android.R.layout.simple_spinner_item,
                        names);

                listView.setAdapter(aa);
            }
        });

        // Listen to Switch
        Switch btnSwitch = (Switch) findViewById(R.id.switch1);
        btnSwitch.setChecked(false);

        btnSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Log.i("Switch", "ON");
                    listView.setVisibility(View.VISIBLE);

                } else {
                    //Log.i("Switch", "OFF");
                    listView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
