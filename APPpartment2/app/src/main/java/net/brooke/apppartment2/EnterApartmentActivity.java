package net.brooke.apppartment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

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
                user.saveInBackground();
                newApartment.saveInBackground();

            }
        });

        //String id = newApartment.getObjectId();


        //newApartment.put("houseID", id);
        //newApartment.add("inhabitants", user.getUsername());
        //user.put("household", id);
        //user.saveInBackground();

        //System.out.println("Successfully created a new apt!, your apt # is : " + id);

        Intent intent = new Intent(EnterApartmentActivity.this, Dashboard_w_NavDrawer.class);
        startActivity(intent);
    }

    public void enterApartment(View v) {
        EditText codeField = (EditText) findViewById(R.id.editText4);
        final String code = codeField.getText().toString();

        ParseQuery<ParseObject> households = ParseQuery.getQuery("Household");
        households.whereEqualTo("houseID", code);

        households.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    System.out.println("Error: Wrong Code. Please try again.");
                } else {
                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("household", code);
                    object.add("inhabitants", user.getUsername());
                    user.saveInBackground();
                    object.saveInBackground();
                    System.out.println("Added to apartment!");

                    Intent intent = new Intent(EnterApartmentActivity.this, Dashboard_w_NavDrawer.class);
                    startActivity(intent);
                }
            }
        });

    }
}

