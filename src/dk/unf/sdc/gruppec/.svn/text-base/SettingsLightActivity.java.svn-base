package dk.unf.sdc.gruppec;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class SettingsLightActivity extends Activity {
	
	private Button confirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_light);
        
        confirmButton = (Button) findViewById(R.id.light_confirm_button);
        confirmButton.setOnClickListener(confirmListener);
    }
    
    private OnClickListener confirmListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
			
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings_light, menu);
        return true;
    }

    
}
