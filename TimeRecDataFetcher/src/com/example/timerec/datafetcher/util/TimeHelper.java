package com.example.timerec.datafetcher.util;


import android.text.format.Time;

public class TimeHelper {

	private static final Logger log = Logger.create(TimeHelper.class);

	private static final String DATEMASK = "%Y-%m-%d";

	static Time getNow() {
		Time t = new Time();
		t.setToNow();
		return t;
	}

	private static String normalizeAndFormat(Time t) {
		t.normalize(true);
		return t.format(DATEMASK);
	}

	public static String getDay(int dayOffset) {
		Time t = getNow();
		t.monthDay += dayOffset;
		return t.format(DATEMASK);
	}

	public static String getFirstOfMonth(int monthOffset) {
		Time t = getNow();
		t.monthDay = 1;
		t.month += monthOffset;
		return normalizeAndFormat(t);
	}

	public static String getFirstOfWeek(int weekOffset) {
		Time t = getNow();
		t.weekDay = 0;
		t.normalize(true);
		t.monthDay += weekOffset*7;
		return normalizeAndFormat(t);
	}

	public static void testAll() {
		log.debug("today", getDay(0));
		log.debug("yesterday", getDay(-1));
		log.debug("tomorrow", getDay(+1));

		log.debug("this week", getFirstOfWeek(0));
		for (int i=-6;i<6;i++) {
			log.debug("week "+i, getFirstOfWeek(i));
		}

		log.debug("this month", getFirstOfMonth(0));
		for (int i=-6;i<6;i++) {
			log.debug("month "+i, getFirstOfMonth(i));
		}
	}

}
