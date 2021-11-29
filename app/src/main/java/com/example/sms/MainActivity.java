package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.acl.Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendSms(View v) {
        SmsManager manager = SmsManager.getDefault();
        StringBuilder number = new StringBuilder();
        number.append("+");
        number.append(((TextView)findViewById(R.id.countryInput)).getText());
        number.append(((TextView)findViewById(R.id.numberInput)).getText());
        manager.sendTextMessage(number.toString(), null, ((TextView)findViewById(R.id.contentInput)).getText().toString(), null, null);
    }
}