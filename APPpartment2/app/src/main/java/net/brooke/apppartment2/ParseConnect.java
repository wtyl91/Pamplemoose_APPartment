package net.brooke.apppartment2;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseFacebookUtils;

/**
 * Created by mandy_000 on 10/30/2015.
 * This class establishes connection b/w app and parse
 * It is run ONCE right when app is launched
 */
public class ParseConnect extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "f4P9dlTd7ZkYCpAy2SFCAPVfIq5KtkwDdDW2lk1c", "ucqXajPCK1zrVooXgFR5iDzOoJCQBZzmZiUs09jn");

        ParseFacebookUtils.initialize(this);

        // test connection
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

    }
}
