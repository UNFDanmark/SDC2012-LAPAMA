package dk.unf.sdc.gruppec;

import dk.unf.sdc.gruppec.R.id;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsCountryActivity extends Activity {

	private Button countryConfirmButton;
	private Button countryResetButton;
	private Button countryCancelButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_country);
        
        countryConfirmButton = (Button) findViewById(R.id.country_confirm_button);
        countryCancelButton = (Button) findViewById(R.id.country_cancel_button);
        countryResetButton = (Button) findViewById(R.id.country_reset_button);
        countryConfirmButton.setOnClickListener(countryConfirmListener);
        countryCancelButton.setOnClickListener(countryCancelListener);
        countryResetButton.setOnClickListener(countryResetListener);
    }
    
    private OnClickListener countryConfirmListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
			
		}
	};
	
	private OnClickListener countryResetListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
			
		}
	};
	
	private OnClickListener countryCancelListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
			
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings_country, menu);
        return true;
    }

    
}
