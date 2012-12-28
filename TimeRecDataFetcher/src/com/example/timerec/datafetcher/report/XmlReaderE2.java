package com.example.timerec.datafetcher.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.example.timerec.datafetcher.util.Logger;

public class XmlReaderE2 {

	private static final Logger log = Logger.create(XmlReaderE2.class);

	public static final String ENCODING = "UTF-8";

	private static final String TAG_WORKUNIT = "workunit";
	private static final String TAG_GRAND_TOTAL = "grandtotal";

	private final XmlPullParser parser;
	private int evtype;

	public final ArrayList<HashMap<String, String>> workUnits = new ArrayList<HashMap<String,String>>();
	public HashMap<String, String> grandTotal;

	public XmlReaderE2(File xmlfile) throws Exception {
		InputStream fis = new FileInputStream(xmlfile);
		parser = Xml.newPullParser();
		parser.setInput(fis, ENCODING);
	}

	private boolean nextItem() throws Exception {
		parser.next();
		evtype = parser.getEventType();
		return evtype!=XmlPullParser.END_DOCUMENT;
	}

	public void read() throws Exception {

		while(nextItem()) {

			if (evtype==XmlPullParser.START_TAG) {
				String tag = parser.getName();
				if (TAG_WORKUNIT.equals(tag)) {
					workUnits.add(fetchRow(TAG_WORKUNIT));
				}
				else if (TAG_GRAND_TOTAL.equals(tag)) {
					grandTotal = fetchRow(TAG_GRAND_TOTAL);
				}
			}

			if (evtype==XmlPullParser.END_DOCUMENT) {
				break;
			}

		}
	}

	private HashMap<String, String> fetchRow(String startingTag) throws Exception {
		HashMap<String, String> row = new HashMap<String, String>();

		while (nextItem()) {

			String tag = parser.getName();
			if (startingTag.equals(tag) && evtype==XmlPullParser.END_TAG) {
				log.debug("fetch done (1)", startingTag, row);
				return row;
			}

			if (evtype==XmlPullParser.START_TAG) {
				nextItem();
				row.put(tag, parser.getText());
			}

		}

		log.debug("fetch done (2)", startingTag, row);
		return row;
	}

}
