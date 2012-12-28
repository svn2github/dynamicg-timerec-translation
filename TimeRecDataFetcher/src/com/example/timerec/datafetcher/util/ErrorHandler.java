package com.example.timerec.datafetcher.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

import android.os.Environment;

public class ErrorHandler {

	public static void dumpError(Throwable exception) {
		exception.printStackTrace();
		String str = getFullStackTrace(exception);
		File file = new File(Environment.getExternalStorageDirectory(), "time-rec-error.txt");
		try {
			writeFile(file, str);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static String getFullStackTrace(Throwable exception) {
		if (exception==null) {
			return "<no exception>";
		}
		StringWriter sw = new StringWriter() ;
		exception.printStackTrace(new PrintWriter(sw)) ;
		return sw.getBuffer().toString();
	}

	private static void writeFile(File file, String s)
			throws Exception {
		FileWriter w = new FileWriter(file);
		w.append(s);
		w.flush();
		w.close();
	}


}
