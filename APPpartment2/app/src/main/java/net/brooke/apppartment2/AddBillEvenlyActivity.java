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
    public static final String[] testStrings = {"Sean", "Brooke", "Le", "Tom", "Mandy"};

    public void evenlyAddBill(View v) {

        final ListView listView = (ListView) findViewById(R.id.listView);


        // Listen to Switch
        Switch btnSwitch = (Switch) findViewById(R.id.switch1);
        btnSwitch.setChecked(false);
        //btnToggle = (ToggleButton) findViewById(R.id.toggle_1);

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

        ListView listView = (ListView) findViewById(R.id.listView);
        //listView.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                testStrings);

        listView.setAdapter(aa);








    }


}
