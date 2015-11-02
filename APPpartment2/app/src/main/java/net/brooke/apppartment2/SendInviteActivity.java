package net.brooke.apppartment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SendInviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_invite);
    }

    public void dashboard(View v) {
        Intent intent = new Intent(SendInviteActivity.this, Dashboard_w_NavDrawer.class);
        startActivity(intent);
    }
}
