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

public class EnterApartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_apartment);
    }

    public void createApartment(View v) {
        // gonna need this: object.put("houseID", code);
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

