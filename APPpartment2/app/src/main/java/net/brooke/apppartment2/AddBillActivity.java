package net.brooke.apppartment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AddBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.appartment_logo_red);

    }

    public void selectRoomates(View v) {
        Intent intent = new Intent(AddBillActivity.this, SelectRoomatesActivity.class);
        startActivity(intent);
    }

}
