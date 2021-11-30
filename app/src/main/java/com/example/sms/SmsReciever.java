package com.example.sms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReciever extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage message;
        String strMessage = "";
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get("pdus");

        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);

            for (int i = 0; i < pdus.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    message = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    message = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                // Build the message.
                strMessage += "SMS from " + message.getOriginatingAddress();
                strMessage += ": " + message.getMessageBody() + "\n";

                // Create main activity intent to give the received sms
                Intent mainActivity = new Intent(context, MainActivity.class);
                mainActivity.putExtra("Message", strMessage);

                // Set flag to create new activity if its already running
                mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(mainActivity);
            }
        }
    }
}