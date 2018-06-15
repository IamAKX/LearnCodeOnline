package com.akash.applications.learncodeonline;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.applications.learncodeonline.Constants.Utils;
import com.akash.applications.learncodeonline.SavePreferences.UserDetails;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import swarajsaaj.smscodereader.interfaces.OTPListener;
import swarajsaaj.smscodereader.receivers.OtpReader;

public class OTPVerification extends Activity implements OTPListener {
    TextView otpTimmer,phone;
    EditText otpEditText;
    Button resendOtpBtn;
    ImageButton nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_otpverification);

        findViewById(R.id.changeMobileNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        phone = findViewById(R.id.tvMobileNumber);
        phone.setText(getIntent().getStringExtra("phone"));
        nextBtn = findViewById(R.id.goNext);
        nextBtn.setVisibility(View.GONE);
        otpTimmer = findViewById(R.id.otpTimmer);
        otpTimmer.setText("TIME REMAINING 01:00");
        otpEditText = findViewById(R.id.otp);
        resendOtpBtn = findViewById(R.id.resendBtn);
        resendOtpBtn.setEnabled(true );

        resendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOtpBtn.setEnabled(false);
                startTimerThread();
            }
        });

        otpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if(!str.equals("") && !str.matches("\\d+"))
                    otpEditText.setError("OTP contains digit");
                if(str.length()==4)
                    nextBtn.setVisibility(View.VISIBLE);
                else
                    nextBtn.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        startTimerThread();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserDetails(getBaseContext()).setUserPhone(phone.getText().toString());
                startActivity(new Intent(getBaseContext(),NameEmail.class));
            }
        });

        OtpReader.bind(this,"AD-NOTICE");
    }

    private void startTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            private long startTime = 59;
            public void run() {
                while (startTime >0) {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            otpTimmer.setText("TIME REMAINING 00:"+String.format("%02d", startTime--));
                        }
                    });
                }

            }
        };
        new Thread(runnable).start();
        new SendOTP().execute();
    }

    @Override
    public void otpReceived(String messageText) {
        String otp = messageText.substring(messageText.length()-4);
        otpEditText.setText(otp);
        new SendOTP().execute();
    }

    private class SendOTP extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String _OtpNumber = String.format("%04d", new Random().nextInt(10000));

            final String composedOtpMessage = "Your LearnCodeOnline mobile verification OTP is "+_OtpNumber;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.SMS_ANY_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("Checking", response);

                            //If we are getting success from server
                            Toast.makeText(getBaseContext(),response,Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                            Log.i("Checking", error + " ");

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put("phoneNumber", phone.getText().toString());
                    params.put("message",composedOtpMessage);
                    params.put("accessKey", Utils.SMS_ANY_API);
                    return params;
                }
            };

            //Adding the string request to the queue
            stringRequest.setShouldCache(false);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
            requestQueue.getCache().clear();

            return null;
        }
    }
}
