package com.example.timerec.datafetcher;

import java.io.File;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class TimeRecDataFetcher {

	public static interface FileCreatedListener {
		public void received(File file);
	}

	/**
	 * 
	 * @param context
	 * @param exportType E1 to T6
	 * @param dateFrom
	 * @param dateTo
	 * @param exportFormat html, csv, xml
	 * @param listener the callback
	 */
	public static void callDataExport(Context context
			, String exportType
			, String dateFrom
			, String dateTo
			, String exportFormat
			, final FileCreatedListener listener
			)
	{

		Intent intent = new Intent("com.dynamicg.timerecording.DATA_EXPORT");
		// -->> set package if you have both FREE and PRO
		// intent.setPackage("");
		intent.putExtra("com.dynamicg.timerecording.DATE_FROM", dateFrom);
		intent.putExtra("com.dynamicg.timerecording.DATE_TO", dateTo);
		intent.putExtra("com.dynamicg.timerecording.EXPORT_TYPE", exportType);
		intent.putExtra("com.dynamicg.timerecording.EXPORT_FORMAT", exportFormat);

		final String KEY_RESULT_FILE = "com.dynamicg.timerecording.FILE";

		BroadcastReceiver resultReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent resultIntent) {
				Bundle bundle = this.getResultExtras(true);
				String filepath = bundle.getString(KEY_RESULT_FILE);
				File file = new File(filepath);
				listener.received(file);
			}
		};

		context.sendOrderedBroadcast(intent, null, resultReceiver
				, null, Activity.RESULT_OK, null, null);

	}


}
