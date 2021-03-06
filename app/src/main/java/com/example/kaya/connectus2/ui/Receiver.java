package com.example.kaya.connectus2.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;


public class Receiver extends BroadcastReceiver {

    Ringtone ringtone;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"WakeUpTodayOffice",Toast.LENGTH_SHORT).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }
}
