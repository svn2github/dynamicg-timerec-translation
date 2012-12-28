package com.example.timerec.datafetcher.util;


import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class TTSHelper implements OnInitListener {

	private static final Logger log = Logger.create(TTSHelper.class);

	private static boolean SPEECH_ENABLED = true;

	private final TextToSpeech talker;
	private final String[] text;

	public TTSHelper(Context context, String... text) {
		this.text = text;
		this.talker = new TextToSpeech(context, this);
	}

	//	private String concat() {
	//		StringBuilder sb = new StringBuilder();
	//		for (String str:text) {
	//			if (str==null || str.length()==0) {
	//				continue;
	//			}
	//			if (sb.length()>0) {
	//				sb.append(", ");
	//			}
	//			sb.append(str);
	//		}
	//		return sb.toString();
	//	}

	@Override
	public void onInit(int status) {
		for (String str:text) {
			if (str!=null && str.trim().length()>0) {
				log.debug("SAY", str);
				if (SPEECH_ENABLED) {
					talker.speak(str, TextToSpeech.QUEUE_ADD, null);
				}
			}
		}
	}

}
