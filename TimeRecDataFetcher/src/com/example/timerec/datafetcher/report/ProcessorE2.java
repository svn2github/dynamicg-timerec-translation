package com.example.timerec.datafetcher.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.example.timerec.datafetcher.TimeRecDataFetcher;
import com.example.timerec.datafetcher.TimeRecDataFetcher.FileCreatedListener;
import com.example.timerec.datafetcher.util.ErrorHandler;
import com.example.timerec.datafetcher.util.Logger;
import com.example.timerec.datafetcher.util.TTSHelper;
import com.example.timerec.datafetcher.util.TextBuffer;
import com.example.timerec.datafetcher.util.TimeHelper;

public class ProcessorE2 {

	private static final Logger log = Logger.create(ProcessorE2.class);

	private final Context context;

	public ProcessorE2(Context context) {
		this.context = context;
		fetchAndSpeakToday();
	}

	private void fetchAndSpeakToday() {
		String exportType = "e2";
		String exportFormat = "xml";
		String today = TimeHelper.getDay(0);

		FileCreatedListener listener = new FileCreatedListener() {
			@Override
			public void received(File file) {
				try {
					log.debug("export file received", file);
					process(file);
				}
				catch (Throwable t) {
					ErrorHandler.dumpError(t);
					throw new RuntimeException(t);
				}
			}
		};
		TimeRecDataFetcher.callDataExport(context, exportType, today, today, exportFormat, listener);
	}

	private static String stripLeadingZero(String s) {
		if (s.startsWith("0")) {
			s = s.substring(1);
		}
		return s;
	}

	static String escapeTime(String s) {
		if (s==null||s.length()==0) {
			return "";
		}
		System.err.println("S="+s);
		String[] time = s.split(s.contains(".") ? "\\." : ":");
		return "Hours "+stripLeadingZero(time[0])
				+" Minutes "+stripLeadingZero(time[1])
				;
	}

	/*
	 * see "sample.timerec.20121228.20121228.e2.xml" for complete E2 sample
	 */
	private void process(File file) throws Exception {
		XmlReaderE2 reader = new XmlReaderE2(file);
		reader.read();

		TextBuffer buffer = new TextBuffer();

		ArrayList<HashMap<String, String>> workUnits = reader.workUnits;
		HashMap<String, String> lastWorkUnit = workUnits.size()>0 ? workUnits.get(workUnits.size()-1) : null;
		if (lastWorkUnit!=null) {
			buffer.append("Task", lastWorkUnit.get("taskTitle"));
			buffer.append("Time", escapeTime(lastWorkUnit.get("timeTotal")));
			buffer.append("Amount", lastWorkUnit.get("amount"));
		}

		HashMap<String, String> grandTotal = reader.grandTotal;
		if (grandTotal!=null) {
			buffer.append("Grand Total Time", escapeTime(grandTotal.get("time")));
			buffer.append("Grand Total Amount", grandTotal.get("amount"));
		}

		new TTSHelper(context, buffer.get());
	}

}
