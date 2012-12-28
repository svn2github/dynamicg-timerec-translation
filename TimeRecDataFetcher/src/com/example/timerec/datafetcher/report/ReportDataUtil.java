package com.example.timerec.datafetcher.report;

public class ReportDataUtil {

	public static String stripLeadingZero(String s) {
		if (s.startsWith("0")) {
			return s.substring(1);
		}
		return s;
	}

	public static String escapeTime(String s) {
		if (s==null||s.length()==0) {
			return "";
		}
		String[] time = s.split(s.contains(".") ? "\\." : ":");
		return "Hours "+stripLeadingZero(time[0])
				+" Minutes "+stripLeadingZero(time[1])
				;
	}

}
