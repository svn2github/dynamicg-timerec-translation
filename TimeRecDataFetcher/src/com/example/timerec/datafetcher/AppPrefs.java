package com.example.timerec.datafetcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPrefs {

	private static final String PREFS = "prefs";
	private static final String KEY_E2 = "E2";
	private static final String KEY_REPEAT = "Repeat";

	public final SharedPreferences prefs;

	public AppPrefs(Context context) {
		prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
	}

	public void writePrefs(int repeat, boolean toggleE2) {
		Editor edit = prefs.edit();
		edit.putBoolean(KEY_E2, toggleE2);
		edit.putInt(KEY_REPEAT, repeat);
		edit.commit();
	}

	public boolean isE2() {
		return prefs.getBoolean(KEY_E2, false);
	}

	public int getRepeatMM() {
		return prefs.getInt(KEY_REPEAT, 30);
	}

	public long getRepeatAsMilli() {
		int repeat = prefs.getInt(KEY_REPEAT, 30);
		if (repeat==999) {
			return 15*1000l; // n secs
		}
		return repeat * 60l * 1000l;
	}

	public boolean isDisabled() {
		return isE2()==false;
	}

}
