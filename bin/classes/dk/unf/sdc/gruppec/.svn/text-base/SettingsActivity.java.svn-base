package dk.unf.sdc.gruppec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsActivity extends Activity {

	private Button defaultButton;
	private Button countryButton;
	private Button lightButton;
	private Button intervalButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        defaultButton = (Button) findViewById(R.id.change_default_button);
        countryButton = (Button) findViewById(R.id.foreign_country_button);
        lightButton = (Button) findViewById(R.id.light_manager_button);
        intervalButton = (Button) findViewById(R.id.update_interval_button);
        defaultButton.setOnClickListener(defaultListener);
        countryButton.setOnClickListener(countryListener);
        lightButton.setOnClickListener(lightListener);
        intervalButton.setOnClickListener(intervalListener);
        
    }
    
    private OnClickListener defaultListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intentDefaultSettings = new Intent(getApplicationContext(), SettingsDefaultActivity.class);
			startActivity(intentDefaultSettings);
			
		}
	};
	
	private OnClickListener countryListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intentCountrySettings = new Intent(getApplicationContext(), SettingsCountryActivity.class);
			startActivity(intentCountrySettings);
			
		}
	};
	
	private OnClickListener lightListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intentLightSettings = new Intent(getApplicationContext(), SettingsLightActivity.class);
			startActivity(intentLightSettings);
			
		}
	};
	
	private OnClickListener intervalListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intentIntervalSettings = new Intent(getApplicationContext(), SettingsIntervalActivity.class);
			startActivity(intentIntervalSettings);
			
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }

    
}
