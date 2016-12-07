package dk.unf.sdc.gruppec;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsIntervalActivity extends Activity {
	
	private Button updateButton;
	private Button cancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_interval);
        
        updateButton = (Button) findViewById(R.id.interval_update_button);
        cancelButton = (Button) findViewById(R.id.interval_cancel_button);
        updateButton.setOnClickListener(updateListener);
        cancelButton.setOnClickListener(cancelListener);
    }
    
    private OnClickListener updateListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
			
		}
	};
	
	private OnClickListener cancelListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
			
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings_interval, menu);
        return true;
    }

    
}
