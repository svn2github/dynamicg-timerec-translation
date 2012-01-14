package com.dynamicg.timerec.support;

import java.io.File;

public class FolderList {

	private StringBuffer sb = new StringBuffer();
	int counter=0;
	final int MAX_ENTRIES = 2000;
	
	public FolderList(TimeRecSupportMain activity) {
		File root = new File("/");
		dumpSubFiles(root, 0);
		MailSender.sendMail(activity, sb.toString());
	}
	
	public void dumpSubFiles(File folder, int depth) {
		
		String path = folder.getAbsolutePath();
		if (path.startsWith("/sys/")
				|| path.startsWith("/proc/") 
				|| path.startsWith("/d/") 
				|| path.startsWith("/dev/") 
				|| path.startsWith("/acct/") 
				|| path.startsWith("/vendor/") 
				|| path.startsWith("/etc/") 
				|| path.startsWith("/system/") 
				) 
		{
			return;
		}
		
		counter++;
		if (counter>MAX_ENTRIES) {
			return;
		}
		
		if (folder.isDirectory()) {
			sb.append(folder.getAbsolutePath()+"\n");
			File[] files = folder.listFiles();
			if (files!=null) {
				for (File dir:files) {
					if (dir.isDirectory() && depth<=8) {
						dumpSubFiles(dir, depth+1);
					}
				}
			}
		}
		
	}
	
}
