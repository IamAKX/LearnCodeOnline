package com.akash.applications.learncodeonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.akash.applications.learncodeonline.Constants.PreferenceKey;
import com.akash.applications.learncodeonline.SavePreferences.UserDetails;

public class NameEmail extends Activity {
    ImageButton nextBtn;
    EditText name,email;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_name_email);

        context = getBaseContext();
        nextBtn = findViewById(R.id.goNext);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") || email.getText().toString().equals(""))
                {
                    Toast.makeText(context,"Enter Name and Email to proceed",Toast.LENGTH_LONG).show();
                    return;
                }

                new UserDetails(context).setUserName(name.getText().toString());
                new UserDetails(context).setUserEmail(email.getText().toString());
                Intent i = new Intent(getBaseContext(),Home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });



        if(!new UserDetails(context).getUserName().equalsIgnoreCase(PreferenceKey.NO_NAME_FOUND))
            name.setText(new UserDetails(context).getUserName());

        if(!new UserDetails(context).getUserEmail().equalsIgnoreCase(PreferenceKey.NO_EMAIL_FOUND))
            email.setText(new UserDetails(context).getUserEmail());
    }
}
