package com.example.timerec.datafetcher;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class WakeUpService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Context context = this;
		AppPrefs prefs = new AppPrefs(context);
		if (prefs.isE2()) {
			AlarmHelper.processE2(context);
		}
		this.stopSelf();
		return 0;
	}

}
