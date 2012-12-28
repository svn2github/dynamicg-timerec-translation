package com.example.timerec.datafetcher;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.timerec.datafetcher.report.ProcessorE2;
import com.example.timerec.datafetcher.util.ErrorHandler;

public class AlarmHelper {

	public static void triggerAlarm(Context context, boolean immediate) {
		AppPrefs prefs = new AppPrefs(context);
		AlarmManager am = (AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);

		Intent broadcast = new Intent(context, AlarmReceived.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, broadcast, 0);
		am.cancel(alarmIntent); // cancel other alarms (if "save" has been clicked multiple times with different interval)

		if (prefs.isDisabled()) {
			return;
		}

		long nextRun = System.currentTimeMillis();
		if (immediate) {
			nextRun += 1000l;
		}
		else {
			nextRun += prefs.getRepeatAsMilli();
		}

		am.set(AlarmManager.RTC_WAKEUP, nextRun, alarmIntent);
	}

	//	public static void startRepeatingAlarm(Context context) {
	//		AlarmManager am = (AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
	//		AppPrefs prefs = new AppPrefs(context);
	//		long triggerAtMillis = System.currentTimeMillis() + 1500l;
	//
	//		Intent broadcast = new Intent(context, AlarmReceived.class);
	//		PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, broadcast, 0);
	//
	//		if (prefs.isDisabled()) {
	//			am.cancel(alarmIntent);
	//			return;
	//		}
	//
	//		am.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, prefs.getRepeatAsMilli(), alarmIntent);
	//	}

	public static void processE2(Context context) {
		try {
			new ProcessorE2(context);
		}
		catch (Throwable t) {
			ErrorHandler.dumpError(t);
			throw new RuntimeException(t);
		}
	}

}
