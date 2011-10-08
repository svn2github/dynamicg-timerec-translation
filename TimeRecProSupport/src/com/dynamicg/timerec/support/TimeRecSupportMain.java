package com.dynamicg.timerec.support;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimeRecSupportMain extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView status1 = (TextView)findViewById(R.id.status1);
        final TextView status2 = (TextView)findViewById(R.id.status2);
        final TextView status3 = (TextView)findViewById(R.id.status3);
        
        String path = getFilesDir().getAbsolutePath();
        path = path.replaceFirst("com.dynamicg.timerec.support"
        		, "com.dynamicg.timerecording.pro"
        		);
        
        status1.setText("Path: ["+getFilesDir().getAbsolutePath()+"]["+path+"]");
        
        //final File dbfile = new File("/data/data/com.dynamicg.timerecording.pro/files/timeRecording.db");
        final File dbfile = new File(path+"/timeRecording.db");
        
        status2.setText("Database file="+dbfile
        		+"\ncanRead="+dbfile.canRead());
        
        Button buttonCopyDatabase = (Button)findViewById(R.id.buttonCopy);
        buttonCopyDatabase.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File sdcard = Environment.getExternalStorageDirectory();
				try {
					File copiedFile = FileUtil.copy(dbfile, sdcard, "timeRecording-pro.db");
					status3.setText("\u2192 Database copied ["+copiedFile+"]");
				}
				catch (Throwable t) {
					status3.setText("ERROR:"+t.toString());
				}
			}
		});
        
    }
}