package com.dynamicg.timerecinfogetter;

import java.util.TreeSet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class TimeRecDataGetter {

	private Context context;
	private TextView output;

	public TimeRecDataGetter(Context context, TextView output) {
		this.context = context;
		this.output = output;
	}

	private static String getMatchingTimeRecPackage(Context context, String... packages) {
		for (String pkg:packages) {
			try {
				if (context.getPackageManager().getPackageInfo(pkg, PackageManager.GET_ACTIVITIES)!=null) {
					return pkg;
				}
			} catch (NameNotFoundException e) {}
		}
		return null;
	}

	public void getData() {
		Intent intent = new Intent("com.dynamicg.timerecording.GET_INFO");
		intent.setPackage(getMatchingTimeRecPackage(context, "com.dynamicg.timerecording.pro", "com.dynamicg.timerecording"));
		intent.putExtra("com.dynamicg.timerecording.FLAGS", 1); // 1=resolve weekly delta
		BroadcastReceiver resultReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent resultIntent) {
				Bundle extras = this.getResultExtras(true);
				Bundle result = extras.getBundle("com.dynamicg.timerecording.RESULT");
				debugBundle(result);
				getFields(result);
			}
		};
		context.sendOrderedBroadcast(intent, null, resultReceiver, null, Activity.RESULT_OK, null, null);
	}

	private void getFields(Bundle bundle) {
		if (bundle==null) {
			return;
		}
		/*
		 * --formats--
		 * DATETIME: yyyy-mm-dd hh24:mi:ss
		 * TIME_TOTAL: hh24:mi
		 */
		print("Daily work time (seconds)", bundle.getLong("TIME_TOTAL_SECS"));
		print("Daily work time (formatted)", bundle.getString("TIME_TOTAL_FORMATTED"));
		print("Amount (double)", bundle.getDouble("AMOUNT_TOTAL"));
		print("Amount (formatted)", bundle.getString("AMOUNT_TOTAL_FORMATTED"));
		print("Generic Stamp Value 1", bundle.getDouble("VALUE1_TOTAL"));
		print("Generic Stamp Value 2", bundle.getDouble("VALUE2_TOTAL"));
		print("Day notes", bundle.getString("DAY_COMMENT"));
		print("Daily delta (+/- seconds)", bundle.getLong("DELTA_DAY_SECS"));
		print("Daily delta (formatted)", bundle.getString("DELTA_DAY_FORMATTED"));
		print("Weekly delta (+/- seconds)", bundle.getLong("DELTA_WEEK_SECS"));
		print("Weekly delta (formatted)", bundle.getString("DELTA_WEEK_FORMATTED"));
		print("Alarm time 'Daily target reached'", bundle.getString("DAY_TARGET_REACHED"));
		print("Alarm time 'Daily worktime exeeded'", bundle.getString("DAY_MAX_TIME_THRESHOLD"));
		print("Alarm time 'Weekly target reached'", bundle.getString("WEEK_TARGET_REACHED"));
		print("Alarm time 'Weekly worktime exeeded'", bundle.getString("WEEK_MAX_TIME_THRESHOLD"));
		print("Checked-in flag", bundle.getBoolean("CHECKED_IN"));
		print("Number of work units", bundle.getInt("NUM_WORK_UNITS"));
		print("Task ID", bundle.getInt("TASK_ID"));
		print("Task", bundle.getString("TASK"));
		print("Customer", bundle.getString("CUSTOMER"));
		print("Check-in time", bundle.getString("CHECK_IN_TIME"));
		print("Check-out time", bundle.getString("CHECK_OUT_TIME"));
		print("Work unit notes", bundle.getString("WORK_UNIT_COMMENT"));
	}

	private static void debugBundle(Bundle bundle) {
		System.err.println(".bundle "+(bundle==null?"NULL":""));
		if (bundle==null) {
			return;
		}
		TreeSet<String> keys = new TreeSet<String>(bundle.keySet());
		for (String key:keys) {
			System.err.println(". <"+key+"> = <"+bundle.get(key)+">");
		}
	}

	private void print(String label, Object o) {
		output.append(label+": "+(o!=null?o.toString():"<null>")+"\n");
	}

}
