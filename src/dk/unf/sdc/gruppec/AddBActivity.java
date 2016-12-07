package dk.unf.sdc.gruppec;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import dk.unf.sdc.gruppec.database.BookmarkManagerDatabaseConnection;
import dk.unf.sdc.gruppec.data.Bookmark;
import dk.unf.sdc.gruppec.data.Coordinate;
import dk.unf.sdc.gruppec.data.State;

public class AddBActivity extends Activity {

	private EditText nameText;
	private EditText radiusText;
	private RadioGroup wifiRadioGroup;
	private RadioGroup mobileNetworkRadioGroup;
	private RadioGroup bluetoothRadioGroup;
	private RadioGroup muteRadioGroup;
	private RadioGroup vibratorRadioGroup;
	private RadioGroup flightmodeRadioGroup;
	private CheckBox wifiRevertCheckBox;
	private CheckBox mobileNetworkRevertCheckBox;
	private CheckBox bluetoothRevertCheckBox;
	private CheckBox muteRevertCheckBox;
	private CheckBox vibratorRevertCheckBox;
	private CheckBox flightmodeRevertCheckBox;
	private CheckBox revertAllCheckBox;
	private Button confirmBookmarkButton;
	private Button resetAddBookmarButton;
	private Button deleteBookmarkButton;
	private Button cancelAddBookmarkButton;
	private ScrollView AddBMActScrollView;
	private Button takePositionButton;
	private Coordinate position;
	private BookmarkManagerDatabaseConnection database;
	private int bookmarkID;
	private Bookmark bookmarkToEdit;
	private boolean editing;
//	public final static String addBook = "addbook";
	private LinearLayout layoutPosition;
	private TextView positionLatText;
	private TextView positionLongText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_b);
		
		registerReceiver(tjek, new IntentFilter(Service_Background.addBook));

		nameText = (EditText) findViewById(R.id.name_text_box);
		radiusText = (EditText) findViewById(R.id.radius_text_box);
		wifiRadioGroup = (RadioGroup) findViewById(R.id.wifi_radiogroup);
		mobileNetworkRadioGroup = (RadioGroup) findViewById(R.id.mobile_network_radiogroup);
		bluetoothRadioGroup = (RadioGroup) findViewById(R.id.bluetooth_radiogroup);
		muteRadioGroup = (RadioGroup) findViewById(R.id.mute_radiogroup);
		vibratorRadioGroup = (RadioGroup) findViewById(R.id.vibrator_radiogroup);
		flightmodeRadioGroup = (RadioGroup) findViewById(R.id.flightmode_radiogroup);
		wifiRevertCheckBox = (CheckBox) findViewById(R.id.wifi_revert_checkbox);
		mobileNetworkRevertCheckBox = (CheckBox) findViewById(R.id.mobile_network_revert_checkbox);
		bluetoothRevertCheckBox = (CheckBox) findViewById(R.id.bluetooth_revert_checkbox);
		muteRevertCheckBox = (CheckBox) findViewById(R.id.mute_revert_checkbox);
		vibratorRevertCheckBox = (CheckBox) findViewById(R.id.vibrator_revert_checkbox);
		flightmodeRevertCheckBox = (CheckBox) findViewById(R.id.flightmode_revert_checkbox);
		revertAllCheckBox = (CheckBox) findViewById(R.id.revert_all_checkbox);
		confirmBookmarkButton = (Button) findViewById(R.id.confirm_bookmark_button);
		resetAddBookmarButton = (Button) findViewById(R.id.reset_button);
		deleteBookmarkButton = (Button) findViewById(R.id.delete_bookmark_button);
		cancelAddBookmarkButton = (Button) findViewById(R.id.cancel_button);
		AddBMActScrollView = (ScrollView) findViewById(R.id.add_bookmark_act_scrollview);
		takePositionButton = (Button) findViewById(R.id.take_position_button);
		layoutPosition = (LinearLayout) findViewById(R.id.take_position_layout);
		positionLatText = (TextView) findViewById(R.id.position_latitude_text);
		positionLongText = (TextView) findViewById(R.id.position_longitude_text);
		confirmBookmarkButton.setOnClickListener(confirmBMListener);
		resetAddBookmarButton.setOnClickListener(resetListener);
		deleteBookmarkButton.setOnClickListener(deleteBMListener);
		cancelAddBookmarkButton.setOnClickListener(cancelListener);
		takePositionButton.setOnClickListener(takePositionListener);
		revertAllCheckBox.setOnClickListener(revertAllListener);
		database = new BookmarkManagerDatabaseConnection(
				getApplicationContext());

		editing = false;
		if (getIntent() != null) {
			if (getIntent().getExtras().getBoolean("dk.unf.sdc.gruppec.edit",
					false)) {
				editing = true;
			}
		}

		if (editing) {
			// deleteBookmarkButton.setVisibility(View.VISIBLE);
			// this crappy button doesn't work
			bookmarkID = getIntent().getExtras().getInt(
					"dk.unf.sdc.gruppec.bmid", -1);
			if (bookmarkID == -1) {
				Intent intentAddBM = new Intent(getApplicationContext(),
						AddBActivity.class);
				intentAddBM.putExtra("dk.unf.sdc.gruppec.edit", false);
				startActivityForResult(intentAddBM, 2);
			}

			bookmarkToEdit = database.getBookmark(bookmarkID);
			if (bookmarkToEdit == null) {
				Log.d("Bookmark", "error");
			}

			nameText.setText(bookmarkToEdit.getName());
			position = bookmarkToEdit.getPosition();
			radiusText.setText(((Integer) bookmarkToEdit.getRadius())
					.toString());
			wifiRadioGroup.check(bookmarkToEdit.getWifiState().state);
			mobileNetworkRadioGroup.check(bookmarkToEdit
					.getMobileNetworkState().state);
			bluetoothRadioGroup.check(bookmarkToEdit.getBluetoothState().state);
			muteRadioGroup.check(bookmarkToEdit.getMuteState().state);
			vibratorRadioGroup.check(bookmarkToEdit.getVibratorState().state);
			flightmodeRadioGroup
					.check(bookmarkToEdit.getFlightmodeState().state);
			wifiRevertCheckBox
					.setChecked(bookmarkToEdit.getWifiState().revertOnExit);
			mobileNetworkRevertCheckBox.setChecked(bookmarkToEdit
					.getMobileNetworkState().revertOnExit);
			bluetoothRevertCheckBox.setChecked(bookmarkToEdit
					.getBluetoothState().revertOnExit);
			muteRevertCheckBox
					.setChecked(bookmarkToEdit.getMuteState().revertOnExit);
			vibratorRevertCheckBox
					.setChecked(bookmarkToEdit.getVibratorState().revertOnExit);
			flightmodeRevertCheckBox.setChecked(bookmarkToEdit
					.getFlightmodeState().revertOnExit);
			revertAllCheckBox.setChecked(bookmarkToEdit.isRevertToDefault());
			layoutPosition.setVisibility(View.VISIBLE);
			positionLatText.setText(String.valueOf(position.getLatitude()));
			positionLongText.setText(String.valueOf(position.getLongitude()));
			if (revertAllCheckBox.isChecked()) {
				wifiRevertCheckBox.setEnabled(false);
				mobileNetworkRevertCheckBox.setEnabled(false);
				bluetoothRevertCheckBox.setEnabled(false);
				muteRevertCheckBox.setEnabled(false);
				vibratorRevertCheckBox.setEnabled(false);
				flightmodeRevertCheckBox.setEnabled(false);
			}

		}

	}

	
	private OnClickListener confirmBMListener = new OnClickListener() {

		public void onClick(View v) {
			if(position == null){
				// Modtager intents
				position = new Coordinate(0, 0);
			}
			int rad;
			if (!radiusText.getText().toString().equals("")) {
				rad = Integer.parseInt(radiusText.getText().toString());
			} else {
				rad = 0;
			}

			Bookmark newBookmark;
			if (!editing) {
				newBookmark = new Bookmark(nameText.getText().toString(),
						position, rad, revertAllCheckBox.isChecked(),
						new State(), new State(), new State(), new State(),
						new State(), new State());
			} else {
				newBookmark = new Bookmark(bookmarkID, nameText.getText()
						.toString(), position, rad,
						revertAllCheckBox.isChecked(), new State(),
						new State(), new State(), new State(), new State(),
						new State());
			}

			newBookmark.getWifiState().state = wifiRadioGroup
					.getCheckedRadioButtonId();
			if (wifiRevertCheckBox.isChecked()) {
				newBookmark.getWifiState().previousOn = false;
			}
			newBookmark.getWifiState().revertOnExit = wifiRevertCheckBox
					.isChecked();

			newBookmark.getMobileNetworkState().state = mobileNetworkRadioGroup
					.getCheckedRadioButtonId();
			if (mobileNetworkRevertCheckBox.isChecked()) {
				newBookmark.getMobileNetworkState().previousOn = false;
			}
			newBookmark.getMobileNetworkState().revertOnExit = mobileNetworkRevertCheckBox
					.isChecked();

			newBookmark.getBluetoothState().state = bluetoothRadioGroup
					.getCheckedRadioButtonId();
			if (bluetoothRevertCheckBox.isChecked()) {
				newBookmark.getBluetoothState().previousOn = false;
			}
			newBookmark.getBluetoothState().revertOnExit = bluetoothRevertCheckBox
					.isChecked();

			newBookmark.getMuteState().state = muteRadioGroup
					.getCheckedRadioButtonId();
			if (muteRevertCheckBox.isChecked()) {
				newBookmark.getMuteState().previousOn = false;
			}
			newBookmark.getMuteState().revertOnExit = muteRevertCheckBox
					.isChecked();

			newBookmark.getVibratorState().state = vibratorRadioGroup
					.getCheckedRadioButtonId();
			if (vibratorRevertCheckBox.isChecked()) {
				newBookmark.getVibratorState().previousOn = false;
			}
			newBookmark.getVibratorState().revertOnExit = vibratorRevertCheckBox
					.isChecked();

			newBookmark.getFlightmodeState().state = flightmodeRadioGroup
					.getCheckedRadioButtonId();
			if (flightmodeRevertCheckBox.isChecked()) {
				newBookmark.getFlightmodeState().previousOn = false;
			}
			newBookmark.getFlightmodeState().revertOnExit = flightmodeRevertCheckBox
					.isChecked();

			if (editing) {
				database.updateBookmark(newBookmark);
			} else {
				database.addBookmark(newBookmark);
			}

			setResult(RESULT_OK);
			finish();

		}
	};

	private OnClickListener resetListener = new OnClickListener() {

		public void onClick(View v) {
			nameText.setText("Bookmark");
			radiusText.setText("15");
			wifiRadioGroup.check(R.id.wifi_unaffected_radio);
			mobileNetworkRadioGroup.check(R.id.mobile_network_unaffected_radio);
			bluetoothRadioGroup.check(R.id.bluetooth_unaffected_radio);
			muteRadioGroup.check(R.id.mute_unaffected_radio);
			vibratorRadioGroup.check(R.id.vibrator_unaffected_radio);
			flightmodeRadioGroup.check(R.id.flightmode_unaffected_radio);
			wifiRevertCheckBox.setEnabled(true);
			mobileNetworkRevertCheckBox.setEnabled(true);
			bluetoothRevertCheckBox.setEnabled(true);
			muteRevertCheckBox.setEnabled(true);
			vibratorRevertCheckBox.setEnabled(true);
			flightmodeRevertCheckBox.setEnabled(true);
			wifiRevertCheckBox.setChecked(true);
			mobileNetworkRevertCheckBox.setChecked(true);
			bluetoothRevertCheckBox.setChecked(true);
			muteRevertCheckBox.setChecked(true);
			vibratorRevertCheckBox.setChecked(true);
			flightmodeRevertCheckBox.setChecked(true);
			revertAllCheckBox.setChecked(false);
			AddBMActScrollView.fling(1000);

		}
	};

	private OnClickListener deleteBMListener = new OnClickListener() {

		public void onClick(View v) {
			database.removeBookmark(bookmarkID);
			setResult(RESULT_OK);
			finish();
		}
	};

	private OnClickListener cancelListener = new OnClickListener() {

		public void onClick(View v) {
			finish();
		}
	};

	private OnClickListener takePositionListener = new OnClickListener() {

		public void onClick(View v) {
			if(position == null){
				// Modtager intents
				position = new Coordinate(0, 0);
			}
			layoutPosition.setVisibility(View.VISIBLE);
			positionLatText.setText(String.valueOf(position.getLatitude()));
			positionLongText.setText(String.valueOf(position.getLongitude()));
			
		}
	};

	private OnClickListener revertAllListener = new OnClickListener() {

		public void onClick(View v) {
			if (revertAllCheckBox.isChecked()) {
				wifiRevertCheckBox.setEnabled(false);
				mobileNetworkRevertCheckBox.setEnabled(false);
				bluetoothRevertCheckBox.setEnabled(false);
				muteRevertCheckBox.setEnabled(false);
				vibratorRevertCheckBox.setEnabled(false);
				flightmodeRevertCheckBox.setEnabled(false);
			} else {
				wifiRevertCheckBox.setEnabled(true);
				mobileNetworkRevertCheckBox.setEnabled(true);
				bluetoothRevertCheckBox.setEnabled(true);
				muteRevertCheckBox.setEnabled(true);
				vibratorRevertCheckBox.setEnabled(true);
				flightmodeRevertCheckBox.setEnabled(true);
			}

		}
	};

	// Modtager intents
	private BroadcastReceiver tjek = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction() == Service_Background.addBook) {
				double lat = intent.getExtras().getDouble("lat");
				double longi = intent.getExtras().getDouble("long");
				position = new Coordinate(lat, longi);
				Toast.makeText(getApplicationContext(), "Position aquired", 3000).show();
			}
		}
	};	
	private Service_Background mtrue;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_add_b, menu);
		return true;
	}
	
	public void onStop()
	{
		super.onStop();
		unregisterReceiver(tjek);
	}

}
