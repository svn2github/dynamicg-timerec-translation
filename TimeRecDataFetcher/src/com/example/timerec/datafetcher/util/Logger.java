package com.example.timerec.datafetcher.util;

import android.util.Log;

public class Logger {

	private static boolean DEBUG_ENABLED = true;

	public final boolean isDebugEnabled = DEBUG_ENABLED;

	private final String textPrefix;

	public static Logger create(Class<?> cls) {
		return new Logger(cls);
	}

	private Logger(Class<?> cls) {
		if (isDebugEnabled) {
			String clname = cls.getName();
			textPrefix = "DG/"+clname.substring(clname.lastIndexOf(".")+1);
		}
		else {
			textPrefix = null;
		}
	}

	private static StringBuilder append(String text, Object... args) {
		StringBuilder sb = new StringBuilder(text);
		for ( int i=0;i<args.length;i++ ) {
			sb.append(" [");
			sb.append(args[i]);
			sb.append("]");
		}
		return sb;
	}

	public void debug(String text) {
		if (!DEBUG_ENABLED) {
			return;
		}
		Log.d(textPrefix, text);
	}

	public void debug(String text, Object... args) {
		if (!DEBUG_ENABLED) {
			return;
		}
		StringBuilder sb = append(text, args);
		Log.d(textPrefix, sb.toString());
	}

}
