package com.example.timerec.datafetcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceived extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, WakeUpService.class);
		context.startService(service);

		AlarmHelper.triggerAlarm(context, false);
	}

}
