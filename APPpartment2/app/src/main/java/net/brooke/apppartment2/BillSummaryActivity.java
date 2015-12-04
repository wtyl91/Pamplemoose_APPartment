package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BillSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbar;
        ParseObject liver;
        int tagNum;
        final String aptCode;
        TextView myTextView;
        FloatingActionButton fab;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_summary);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Adds APPartment Logo to Toolbar
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.appartment_logo_red);

        // Sets the TextView for "Current Balance Owed"
        liver = ParseUser.getCurrentUser();
        tagNum = (int)liver.getNumber("liverNum");
        aptCode = liver.getString("household");
        myTextView = (TextView) findViewById(R.id.textView20);
        getAndDisplayNameSum(aptCode, liver, tagNum, myTextView);

        // Home Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillSummaryActivity.this, Dashboard_w_NavDrawer.class);
                startActivity(intent);
            }
        });
    }

    // Activates once user presses "Pay Bill", updates user's Balance to reflect changes
    public void payBill(View v) {
        EditText payBillValue;
        final String tagName, payBill;
        double amount;
        final ParseObject newBill;

        payBillValue = (EditText) findViewById(R.id.editTextPayBill);
        payBill = payBillValue.getText().toString();

        if (payBill.equals("")) {
            Toast.makeText(getApplicationContext(), "Error: Please enter an amount.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        amount = Double.parseDouble(payBill) * (-1);

        tagName = "inhabitant" + ParseUser.getCurrentUser().getNumber("liverNum").toString();
        newBill = new ParseObject("Bills");
        newBill.put("Creator", ParseUser.getCurrentUser().getString("username"));
        newBill.put("Household", ParseUser.getCurrentUser().getString("household"));
        newBill.put("BillName", "Pay Bill");
        newBill.put(tagName, amount);

        // To see payBillScenario fail, comment this out so the "Pay Bill" Value is not updated
        // in the database:
        newBill.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                newBill.saveInBackground();
                Intent intent = new Intent(BillSummaryActivity.this, Dashboard_w_NavDrawer.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Successfully paid bill!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Slightly delays the execution of the test case in order to allow time for the new bill to
        // post in the Parse database
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        payBillScenario();
                    }
                },
                3000
        );

    }

    // Displays the user's Current Balance owed to the apartment and other roommates
    private void getAndDisplayNameSum(String aptCode, final ParseObject liver, final int liverNum,
                                      final TextView view){

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

    /* Scenario: Given I am on the Bill Summary page, when I enter an amount to "Pay Bill",
       I want that amount to be deducted from my Current Balance.
     */
    private void payBillScenario() {
        // Retrieves information about the current user in order to calculate current balance
        ParseObject testUser = ParseUser.getCurrentUser();
        final int testUserNumber = (int)testUser.getNumber("liverNum");
        final String testUserAptCode = testUser.getString("household");
        ParseQuery<ParseObject> billAmountQuery = ParseQuery.getQuery("Bills");
        billAmountQuery.whereEqualTo("Household", testUserAptCode);

        billAmountQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> billList, ParseException e) {
                DecimalFormat df;
                String currentBalanceString, previousBalanceString, payBillString, oweCol;
                Double currentBalance, previousBalance, payBill, difference, sum;
                TextView previousBalanceValue;
                EditText payBillValue;

                // Calculates the Current Balance of the user from the Parse database
                if (e == null) {
                    oweCol = "inhabitant" + Integer.toString(testUserNumber);
                    sum = 0.0;

                    for (int i = 0; i < billList.size(); i++) {
                        sum += billList.get(i).getDouble(oweCol);
                    }

                    df = new DecimalFormat("0.00");
                    currentBalanceString = df.format(sum);
                    currentBalance = stringToDouble(currentBalanceString);

                    // Retrieves the "Previous" Current Balance value and the Pay Bill Value from the UI
                    previousBalanceValue = (TextView) findViewById(R.id.textView20);
                    payBillValue = (EditText) findViewById(R.id.editTextPayBill);

                    // Converts values to Doubles
                    previousBalanceString = previousBalanceValue.getText().toString();
                    payBillString = payBillValue.getText().toString();
                    previousBalance = stringToDouble(previousBalanceString);
                    payBill = stringToDouble(payBillString);

                    // Calculates the difference between the Previous Balance and the Pay Bill Value
                    difference = previousBalance - payBill;

                    if (currentBalance.equals(difference)) {
                        generateSuccessMessage(previousBalance, payBill, currentBalance);
                    } else {
                        generateFailMessage(previousBalance, payBill, currentBalance, difference);
                    }
                }
            }
        });
    }

    // Helper method for test case to convert string to double and removes '$' symbol
    private Double stringToDouble(String string) {
        final Double value;

        string = string.replace("$", "");
        value = Double.parseDouble(string);

        return value;
    }

    // Helper method to print out Success message for scenario
    private void generateSuccessMessage(Double previousBalance, Double payBill, Double currentBalance) {
        Log.d("Success", "Current Balance matched!");
        System.out.println("Your previous balance was $" + previousBalance + ".");
        System.out.println("You entered $" + payBill + ".");
        System.out.println("Your new balance is $" + currentBalance + ", which matches" +
                " what is in the database.");
    }

    // Helper method to print out Fail message for scenario
    private void generateFailMessage(Double previousBalance, Double payBill, Double currentBalance,
                                     Double difference) {
        Log.d("Fail", "Current Balance did not match");
        System.out.println("Your previous balance was $" + previousBalance + ".");
        System.out.println("You entered $" + payBill + ".");
        System.out.println("Your new balance should be $" + difference + ", which does" +
                " not match the value in the database, $" + currentBalance);
    }
}
