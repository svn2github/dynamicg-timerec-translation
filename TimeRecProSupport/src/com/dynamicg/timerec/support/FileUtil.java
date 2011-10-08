package com.dynamicg.timerec.support;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import android.text.format.Time;

public class FileUtil {

	public static String getContentAsString(File f) 
	throws Exception {
		FileReader r = new FileReader(f);
		StringBuffer sb=new StringBuffer();
		char[] buffer = new char[2048];
		int len;
		while ( (len=r.read(buffer))>0 ) {
			sb.append(buffer, 0, len);
		}
		return sb.toString();
	}

	public static ByteArrayOutputStream getContent(InputStream in, int buffersize) 
	throws Exception {
		byte[] buffer = new byte[buffersize];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len;
		while ( (len=in.read(buffer))>0) {
			out.write(buffer, 0, len);
		}
		in.close();
		return out;
	}

	/*
	 * returns the new file
	 */
	public static File copy(File from, File targetDirectory, String targetFileName) 
	throws IOException {
		
		FileChannel source = new FileInputStream(from).getChannel();
		File targetfile = new File(targetDirectory,targetFileName);
		FileChannel destination = new FileOutputStream(targetfile).getChannel();
		
		destination.transferFrom(source, 0, source.size());
	
		source.close();
		destination.force(true);
		destination.close();
		
		return targetfile;
	}

	/*
	 * returns the new file
	 */
	public static File copy(File from, File targetDirectory) 
	throws IOException {
		String fname = from.getName();
		return copy(from, targetDirectory, fname);
	}
	
	public static String getLastAccessedStrTight(File file) {
		Time t = new Time();
		t.set(file.lastModified());
		return t.format("%d/%m/%Y,%H:%M");
	}
	public static String getLastAccessedStrWide(File file) {
		Time t = new Time();
		t.set(file.lastModified());
		return t.format("%d/%m/%Y, %H:%M");
	}
	
	// see http://android-developers.blogspot.com/2010/12/saving-data-safely.html
	public static boolean sync(FileOutputStream stream) {
		try {
			if (stream != null) {
				stream.flush();
				stream.getFD().sync();
			}
			return true;
		} 
		catch (IOException e) {
			return false;
		}
	}

	public static void writePlain(File file, String s) 
	throws Exception {
		FileWriter w = new FileWriter(file);
		w.append(s);
		w.flush();
		w.close();
	}
	
	public static void delete(File file) {
		if (file!=null && file.exists()) {
			file.delete();
		}
	}
	
}
