package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AddRoommateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_roommate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addExistingUser(View v) {
        Intent intent = new Intent(AddRoommateActivity.this, AddExistingUserActivity.class);
        startActivity(intent);
    }

    public void sendInvite(View v) {
        Intent intent = new Intent(AddRoommateActivity.this, SendInviteActivity.class);
        startActivity(intent);
    }

    /*
     * User clicked Add New User. Email Address field can be accessed so
     * an invite may be sent to user to download app
     *
     */

    /*
    protected void onClickInvite (View view){
        Intent intent = new Intent(AddRoommateActivity.this, Invite.class);
        startActivity(intent);
    } */

    /*
     * User has designated that the roommate they are trying to add is an
     * existing user and already has an account for APPartment
     */

    /*
    protected void onClickExistingUser (View view) {
        Intent intent = new Intent(AddRoommateActivity.this, AddExistingUser.class);
        startActivity(intent);
    }*/

}
