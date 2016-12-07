package dk.unf.sdc.gruppec;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class SettingsDefaultActivity extends Activity {

	private Button defaultConfirmButton;
	private Button defaultResetButton;
	private Button defaultCancelButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_default);

		defaultConfirmButton = (Button) findViewById(R.id.default_confirm_button);
		defaultCancelButton = (Button) findViewById(R.id.default_cancel_button);
		defaultResetButton = (Button) findViewById(R.id.default_reset_button);
		defaultConfirmButton.setOnClickListener(defaultConfirmListener);
		defaultCancelButton.setOnClickListener(defaultCancelListener);
		defaultResetButton.setOnClickListener(defaultResetListener);
	}

	private OnClickListener defaultConfirmListener = new OnClickListener() {

		public void onClick(View v) {
			finish();

		}
	};

	private OnClickListener defaultResetListener = new OnClickListener() {

		public void onClick(View v) {
			finish();

		}
	};

	private OnClickListener defaultCancelListener = new OnClickListener() {

		public void onClick(View v) {
			finish();

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_settings_default, menu);
		return true;
	}

}
