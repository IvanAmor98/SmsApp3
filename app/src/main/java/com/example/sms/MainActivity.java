package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.acl.Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
            }
        }

    }

    public void sendSms(View v) {
        SmsManager manager = SmsManager.getDefault();
        StringBuilder number = new StringBuilder();
        number.append("+");
        number.append(((TextView)findViewById(R.id.countryInput)).getText());
        number.append(((TextView)findViewById(R.id.numberInput)).getText());
        try {
            manager.sendTextMessage(number.toString(), null, ((TextView)findViewById(R.id.contentInput)).getText().toString(), null, null);
            Toast.makeText(MainActivity.this, "SMS enviado", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Error en el envio: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}