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

        // Check version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // If Android version M or newer:
            // Check if already has permissions
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                // If not, asks for permissions
                requestPermissions(new String[] {
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.BROADCAST_SMS
                }, 1);
            }
        }

        // Check if intent has sms received
        if (getIntent().getStringExtra("Message") != null) {
            // If has sms show content
            Toast.makeText(MainActivity.this, "SMS recibido", Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.smsOutput)).setText(getIntent().getStringExtra("Message"));
        }

    }

    public void sendSms(View v) {
        SmsManager manager = SmsManager.getDefault();
        StringBuilder number = new StringBuilder();

        // Build sms address
        number.append("+");
        number.append(((TextView)findViewById(R.id.countryInput)).getText());
        number.append(((TextView)findViewById(R.id.numberInput)).getText());

        try {
            // Try to send sms to default SMSC
            manager.sendTextMessage(number.toString(), null, ((TextView)findViewById(R.id.contentInput)).getText().toString(), null, null);
            Toast.makeText(MainActivity.this, "SMS enviado", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "Error en el envio: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}