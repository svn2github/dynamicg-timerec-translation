package com.example.timerec.datafetcher.util;

import java.util.ArrayList;

public class TextBuffer {

	public final ArrayList<String> strings = new ArrayList<String>();

	public TextBuffer append(String s) {
		strings.add(s);
		return this;
	}

	public void append(String label, String value) {
		if (value!=null && value.trim().length()>0) {
			strings.add(label);
			strings.add(value.trim());
		}
	}

	public void append(String label, String value, boolean condition) {
		if (!condition) {
			return;
		}
		append(label, value);
	}

	public String[] get() {
		return strings.toArray(new String[]{});
	}
}