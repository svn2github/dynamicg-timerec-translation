package com.dynamicg.timerec.support;

import java.util.TreeMap;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

public class CalendarReader {

	private final ContentResolver contentResolver;
	private final Context context;

	public CalendarReader(Context context) {
		this.contentResolver = context.getContentResolver();
		this.context = context;
	}
	
//	private HashMap<String, String> getCalendars() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		Cursor query = contentResolver.query( Uri.parse("content://com.android.calendar/calendars")
//				, new String[] { "_id", "name" } , null, new String[]{}, "");
//		while (query.moveToNext()) {
//			map.put(query.getString(0), query.getString(1));
//		}
//		return map;
//	}
	
	public String readEvents() {
		
		long oneDayMS = 24l * 60l * 60l * 1000l;
		long dtstart = System.currentTimeMillis() - oneDayMS;
		long dtend = System.currentTimeMillis() + oneDayMS;
		
		String selection = "dtstart>=? and dtstart<? and allDay=0";
		String[] selectionArgs = new String[] { Long.toString(dtstart), Long.toString(dtend) };
		
		Cursor c = contentResolver.query ( Uri.parse("content://com.android.calendar/events")
				, null // all
				, selection
				, selectionArgs
				, null
		);
		
		StringBuilder data = new StringBuilder();
		
		while (c.moveToNext()) {
			int columns = c.getColumnCount();
			
			TreeMap<String, String> row = new TreeMap<String, String>();
			for (int i=0;i<columns;i++) {
				row.put(c.getColumnName(i), c.getString(i));
			}
			
			StringBuilder sb = new StringBuilder("=== NEXT ENTRY ===");
			for (String column:row.keySet()) {
				sb.append("\n["+column+"]=["+row.get(column)+"]");
			}
			data.append(sb).append("\n\n");
		}
		
		c.close();
		
		return data.toString();
	}

	public void show() {
		
		final String data = readEvents();
		
		AlertDialog.Builder b = new AlertDialog.Builder(context);
		
		TextView body = new TextView(context);
		body.setText(data);
		
		HorizontalScrollView hscroll = new HorizontalScrollView(context);
		ScrollView vscroll = new ScrollView(context);
		
		hscroll.addView(body);
		vscroll.addView(hscroll);
		b.setView(vscroll);

		b.setPositiveButton("Email", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				final String AUTHOR = "dynamicg.android@gmail.com";
				String title = "Calendar data";
				Intent msg = new Intent(Intent.ACTION_SEND);
				msg.setType("text/plain");
				msg.putExtra(Intent.EXTRA_SUBJECT, title);
				msg.putExtra(Intent.EXTRA_TEXT, data);
				msg.putExtra(Intent.EXTRA_EMAIL, new String[]{AUTHOR} );
				context.startActivity(Intent.createChooser(msg, "Send error report"));
			}
		});

		b.setNegativeButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		b.show();
	}
	
}
