package net.brooke.apppartment2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.parse.ParseUser;

import static android.support.v4.content.res.TypedArrayUtils.getResourceId;

public class RetrieveCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setLogo(R.drawable.appartment_logo_red);

        TextView tvCode = (TextView)findViewById(R.id.textView17);
        ParseUser user = ParseUser.getCurrentUser();
        String household = user.getString("household");
        tvCode.setText(household);
    }

}
