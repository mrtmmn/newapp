package com.example.mmamin.blanklogin;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mIdtLogo;
    Button mTopLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mIdtLogo = (ImageView) findViewById(R.id.logo);

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_for_login);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        mTopLoginButton = (Button) dialog.findViewById(R.id.password_button);
        mTopLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();
        param.y = 150;
        param.width = 600;
        param.height = 650;

        dialog.show();
    }
}
