package com.dynamicg.timerecinfogetter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TimeRecDataGetter timeRecDataGetter = new TimeRecDataGetter(this, (TextView)findViewById(R.id.result));
		timeRecDataGetter.getData();
	}

}
