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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class AddBillEvenlyActivity extends AppCompatActivity {

    public void evenlyAddBill(View v) {
        EditText totalAmount = (EditText) findViewById(R.id.editText5);
        final String code = totalAmount.getText().toString();
        float amount = Float.parseFloat(code);
        System.out.println("Total Amount: " + amount);

        // Assume checkboxes returned an array of liverNums called numList
        ParseObject newbill = new ParseObject("Bills");
        newbill.put("Creator", ParseUser.getCurrentUser().getString("username"));
        newbill.put("TotalAmount", amount);
        int splitCount = numList.length;
        float splitAmount = amount / splitCount;
        for(Number num : numList){
            String tagName = "inhabitant"+ num.toString();

            if(num==ParseUser.getCurrentUser().getNumber("liverNum")){
                newbill.put(tagName, (amount-splitAmount)*(-1));
            }
            else {
                newbill.put(tagName, splitAmount);
            }

        }





        /*System.out.println(ParseUser.getCurrentUser().getString("household"));
        ParseQuery<ParseObject> household = ParseQuery.getQuery("Household");
        household.whereEqualTo("houseID", ParseUser.getCurrentUser().getString("household"));
        household.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject apartment, ParseException e) {
                if (apartment == null) {
                    System.out.println("No Apt.");
                }else{
                    ArrayList<String> availableTags = (ArrayList) apartment.getList("inhabitants");
                    int aptSize = availableTags.size();
                    System.out.println("Apt Size: " + aptSize);

                }
            }
        });*/



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill_evenly);


    }

}
