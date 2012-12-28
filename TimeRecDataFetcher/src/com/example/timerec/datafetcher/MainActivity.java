package com.example.timerec.datafetcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private AppPrefs prefs;
	private ToggleButton toggleE2;
	private EditText interval;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		prefs = new AppPrefs(this);

		toggleE2 = (ToggleButton)findViewById(R.id.toggleE2);
		interval = (EditText)findViewById(R.id.repeatMM);

		toggleE2.setChecked(prefs.isE2());
		interval.setText(Integer.toString(prefs.getRepeatMM()));

		findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				writePrefs();
				AlarmHelper.triggerAlarm(MainActivity.this, true);
			}
		});

	}

	private void writePrefs() {
		int repeat;
		try {
			repeat = Integer.parseInt(interval.getText().toString());
		}
		catch (NumberFormatException e) {
			repeat = 30;
		}
		prefs.writePrefs(repeat, toggleE2.isChecked());
	}

}
