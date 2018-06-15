package com.akash.applications.learncodeonline;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneRegisteration extends Activity {
    EditText mobile;
    ImageButton next;
    BottomSheetDialog dialog;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_registeration);

        mobile = findViewById(R.id.mobileNumber);
        next = findViewById(R.id.goNext);

        //Open Terms of service page

        findViewById(R.id.termsCondition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://learncodeonline.in/terms.php"));
                startActivity(i);
            }
        });

        // Modal BottomSheet
        init_modal_bottomsheet();

        mobile.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();

                if(!s.equals("") && !s.matches("\\d+"))
                    mobile.setError("Mobile number is invalid");
                if(s.length()==10)
                    next.setVisibility(View.VISIBLE);
                else
                    next.setVisibility(View.GONE);

                tv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               startActivity(new Intent(getBaseContext(),Home.class));
//               finish();
                 dialog.show();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private void init_modal_bottomsheet() {
        View modalbottomsheet = getLayoutInflater().inflate(R.layout.mobilecnf_alert, null);

        dialog = new BottomSheetDialog(this);
        dialog.setContentView(modalbottomsheet);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        tv = (TextView)modalbottomsheet.findViewById(R.id.cnfMobileNumber);
        tv.setText(mobile.getText().toString());

        Button btn_cancel = (Button) modalbottomsheet.findViewById(R.id.btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btn_cnf = (Button) modalbottomsheet.findViewById(R.id.btnConfirm);
        btn_cnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(getBaseContext(),OTPVerification.class)
                            .putExtra("phone",tv.getText().toString()));
            }
        });
    }
}


