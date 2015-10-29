package awesome.pamplemoose.profiletext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EditText editFirstName = (EditText)findViewById(R.id.editText_FirstName);
        EditText editLastName = (EditText)findViewById(R.id.editText_FirstName);
        EditText editAddress = (EditText)findViewById(R.id.editText_Address);
        EditText editCity = (EditText)findViewById(R.id.editText_City);
        EditText editState = (EditText)findViewById(R.id.editText_State);
        EditText editEmail = (EditText)findViewById(R.id.editText_Email);
        EditText editPhone = (EditText)findViewById(R.id.editText_Phone);
        EditText editUsername = (EditText)findViewById(R.id.editText_Username);
        EditText editPassword = (EditText)findViewById(R.id.editText_Password);

    }


}
