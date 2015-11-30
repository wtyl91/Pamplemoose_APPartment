package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DecimalFormat;
import java.util.List;

public class BillSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.appartment_logo_red);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Sets the TextView for the Current Balance Owed
        ParseObject liver = ParseUser.getCurrentUser();
        int tagNum = (int)liver.getNumber("liverNum");
        final String aptCode = liver.getString("household");
        TextView myTextView = (TextView) findViewById(R.id.textView20);
        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillSummaryActivity.this, Dashboard_w_NavDrawer.class);
                startActivity(intent);
            }
        });
    }

    public void payBill(View v) {
        EditText payBillValue = (EditText) findViewById(R.id.editTextPayBill);

        final String tagName = "inhabitant" + ParseUser.getCurrentUser().getNumber("liverNum").toString();
        final String payBill = payBillValue.getText().toString();

        if (payBill.equals("")) {
            Toast.makeText(getApplicationContext(), "Error: Please enter total amount of bill.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        double amount = Double.parseDouble(payBill);
        amount = amount * (-1);
        System.out.println("amount = " + amount);

        final ParseObject newBill = new ParseObject("Bills");

        newBill.put("Creator", ParseUser.getCurrentUser().getString("username"));
        newBill.put("Household", ParseUser.getCurrentUser().getString("household"));
        newBill.put("BillName", "Pay Bill");
        newBill.put(tagName, amount);

        newBill.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                newBill.saveInBackground();

                Intent intent = new Intent(BillSummaryActivity.this, Dashboard_w_NavDrawer.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Successfully paid bill!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getAndDisplayNameSum(String aptCode, final ParseObject liver, final int liverNum, final TextView view){

        ParseQuery<ParseObject> billAmountQuery = ParseQuery.getQuery("Bills");
        billAmountQuery.whereEqualTo("Household", aptCode);

        billAmountQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> billList, ParseException e) {
                if (e == null) {
                    String oweCol = "inhabitant" + Integer.toString(liverNum);
                    Log.d("inhabitantQ", oweCol);
                    Double sum = 0.0;

                    for (int i = 0; i < billList.size(); i++) {

                        sum += billList.get(i).getDouble(oweCol);

                    }

                    DecimalFormat df = new DecimalFormat("$0.00");


                    view.setText(df.format(sum));

                }
            }
        });

    }

}
