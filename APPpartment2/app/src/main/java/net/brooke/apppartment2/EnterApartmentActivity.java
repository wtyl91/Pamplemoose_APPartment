package net.brooke.apppartment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Collections;

public class EnterApartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_apartment);
    }

    public void createApartment(View v) {
        final ParseObject newApartment = new ParseObject("Household");

        newApartment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                ParseUser user = ParseUser.getCurrentUser();
                String id = newApartment.getObjectId();
                System.out.println("Successfully created a new apt!, your apt # is : " + id);
                newApartment.put("houseID", id);
                newApartment.add("inhabitants", user.getUsername());
                user.put("household", id);

                // Create array of available liverNums
                ArrayList<Integer> availableTags = new ArrayList<Integer>();
                for (int i = 2; i <= 10; i++) {
                    availableTags.add(i);
                }
                newApartment.put("availableTags", availableTags);


                user.put("liverNum", 1);

                user.saveInBackground();
                newApartment.saveInBackground();

                Intent intent = new Intent(EnterApartmentActivity.this, Dashboard_w_NavDrawer.class);
                startActivity(intent);

            }
        });


    }

    public void enterApartment(View v) {
        EditText codeField = (EditText) findViewById(R.id.editText4);
        final String code = codeField.getText().toString();

        ParseQuery<ParseObject> households = ParseQuery.getQuery("Household");
        households.whereEqualTo("houseID", code);

        households.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject apartment, ParseException e) {
                if (apartment == null) {
                    System.out.println("Error: Wrong Code. Please try again.");
                } else { // TODO: Return error if apartment is already full
                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("household", code);


                    int num;

                    // Get list of available tags
                    ArrayList<Integer> availableTags = (ArrayList) apartment.getList("availableTags");

                    // Sort list
                    Collections.sort(availableTags);

                    // Get the smallest available tag
                    num = availableTags.get(0);

                    // Remove the smallest tag from list
                    availableTags.remove(0);

                    // Assign tag to user
                    user.put("liverNum", num);


                    apartment.add("inhabitants", user.getUsername());
                    apartment.put("availableTags", availableTags);
                    user.saveInBackground();
                    apartment.saveInBackground();

                    System.out.println("Added to apartment!");

                    Intent intent = new Intent(EnterApartmentActivity.this, Dashboard_w_NavDrawer.class);
                    startActivity(intent);
                }
            }
        });

    }
}

