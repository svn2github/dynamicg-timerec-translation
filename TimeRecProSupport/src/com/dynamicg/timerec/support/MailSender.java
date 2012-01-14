package com.dynamicg.timerec.support;

import android.content.Context;
import android.content.Intent;

public class MailSender {

	public static void sendMail(Context context, String body) {
		final String AUTHOR = "dynamicg.android@gmail.com";
		String title = "Calendar data";
		Intent msg = new Intent(Intent.ACTION_SEND);
		msg.setType("text/plain");
		msg.putExtra(Intent.EXTRA_SUBJECT, title);
		msg.putExtra(Intent.EXTRA_TEXT, body);
		msg.putExtra(Intent.EXTRA_EMAIL, new String[]{AUTHOR} );
		context.startActivity(Intent.createChooser(msg, "Send report"));
	}
	
}
