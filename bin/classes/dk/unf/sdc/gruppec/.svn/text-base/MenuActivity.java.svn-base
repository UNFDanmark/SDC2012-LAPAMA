package dk.unf.sdc.gruppec;

// http://developer.android.com/guide/components/services.html - Services

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import dk.unf.sdc.gruppec.Service_Background.LocalBinder;

public class MenuActivity extends Activity {

	private ImageButton bookmarksButton;
	private ImageButton addBMButton;
	private ImageButton helpButton;
	private ImageButton creditsButton;
	private TextView lowerView;
	private TextView bottomView;
	private Service_Background mService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		bookmarksButton = (ImageButton) findViewById(R.id.bookmarks_button);
		addBMButton = (ImageButton) findViewById(R.id.add_bm_button);
		helpButton = (ImageButton) findViewById(R.id.help_button);
		creditsButton = (ImageButton) findViewById(R.id.credits_button);
		lowerView = (TextView) findViewById(R.id.low_text_view);
		bottomView = (TextView) findViewById(R.id.bottom_text_view);
		bookmarksButton.setOnClickListener(bookmarksListener);
		addBMButton.setOnClickListener(addBMListener);
		helpButton.setOnClickListener(helpListener);
		creditsButton.setOnClickListener(creditsListener);

		registerReceiver(tjek, new IntentFilter(Service_Background.Update));
			
	}

	// Service

	@Override
	// Forbinder vores service til vores activity når den starter
	protected void onStart() {
		super.onStart();
		Intent counterService = new Intent(this, Service_Background.class);
		startService(counterService);
		bindService(counterService, mConnection, Context.BIND_AUTO_CREATE);
	}

	// Forbindelsen mellem vores service og activity
	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			mService = binder.getService();
		}

		public void onServiceDisconnected(ComponentName name) {
		}
	};

	// Modtager intents
	private BroadcastReceiver tjek = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("tag", "tag2");
			if (intent.getAction() == Service_Background.Update) {
				String count = intent.getExtras().getString("hello");
				lowerView.setText(String.valueOf(count));
			}
			String distance = intent.getExtras().getString("distance");
			bottomView.setText(String.valueOf(distance));
		}
	};

	// Service

	private OnClickListener bookmarksListener = new OnClickListener() {

		public void onClick(View v) {
			Intent intentBookmarks = new Intent(getApplicationContext(),
					BookmarksActivity.class);
			startActivity(intentBookmarks);
		}
	};

	private OnClickListener addBMListener = new OnClickListener() {

		public void onClick(View v) {
			Intent intentAddBM = new Intent(getApplicationContext(),
					AddBActivity.class);
			intentAddBM.putExtra("dk.unf.sdc.gruppec.edit", false);
			startActivityForResult(intentAddBM, 2);
		}
	};

	private OnClickListener helpListener = new OnClickListener() {

		public void onClick(View v) {
			Intent intentSettings = new Intent(getApplicationContext(),
					HelpActivity.class);
			startActivity(intentSettings);
		}
	};

	private OnClickListener creditsListener = new OnClickListener() {

		public void onClick(View v) {
			Intent intentSettings = new Intent(getApplicationContext(),
					CreditsActivity.class);
			startActivity(intentSettings);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
