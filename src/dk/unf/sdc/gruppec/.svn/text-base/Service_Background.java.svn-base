package dk.unf.sdc.gruppec;

import java.util.ArrayList;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import dk.unf.sdc.gruppeC.database.BookmarkManagerDatabaseConnection;
import dk.unf.sdc.gruppec.data.Bookmark;
import dk.unf.sdc.gruppec.MenuActivity;

public class Service_Background extends Service implements LocationListener {

	public AudioManager audioManager;
	public WifiManager wifiManager;

	@Override
	public void onCreate() {
		super.onCreate();
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
		database = new BookmarkManagerDatabaseConnection(
				getApplicationContext());

	}

	private final IBinder mBinder = new LocalBinder();
	public LocationManager locationManager;
	public final static String Update = "hej";
	public final static String addBook = "addbook";
	private Runnable runnable;
	private ArrayList<Bookmark> bookmarks;
	private BookmarkManagerDatabaseConnection database;

	// Bliver kalder noget vores activity binder til vores service
	@Override
	public IBinder onBind(Intent intent) {
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 1000, 0, this);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 1000, 0, this);
		}

		runnable = new Runnable() {
			public void run() {
				}
		};
		new Thread(runnable).start();
		// Returnerer for at sige at vi er forbundet til activiteten
		return mBinder;

	}

	// Gør at vi kan binde vores activity til denne service
	public class LocalBinder extends Binder {
		Service_Background getService() {
			return Service_Background.this;
		}
	}

	
	// Location
	private double printDistance;
	public double latitude;
	public double longitude;

	public void onLocationChanged(Location location) {
		Log.d("tag", "Location updated");
		// Called when a new location is found by the network location
		// provider.

		latitude = location.getLatitude();
		longitude = location.getLongitude();

		String text = "" + latitude + ", " + longitude + "";
		Intent returnValue = new Intent(Update);
		returnValue.putExtra("hello", text);

		if (database.getBookmark(1) != null) {
			float[] dist = new float[1];
			Location.distanceBetween(this.latitude, this.longitude, database
					.getBookmark(1).getPosition().latitude, database
					.getBookmark(1).getPosition().longitude, dist);
			printDistance = dist[0];
			// distance(Home, latitude, longitude);
			String cat = "" + printDistance + "";
			returnValue.putExtra("distance", cat);
		} else {
			String cat = "No Bookmarks found";
			returnValue.putExtra("distance", cat);
		}
		sendBroadcast(returnValue);
		hello();

		Intent value = new Intent(addBook);
		value.putExtra("lat", latitude);
		value.putExtra("long", longitude);
		sendBroadcast(value);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onProviderDisabled(String provider) {
	}

	// Location slut

	public void hello() {
		bookmarks = database.getBookmarks();
		
		double cLongitude = this.longitude;
		double cLatitude = this.latitude;
		if (bookmarks != null) {
			for (int i = 0; i < bookmarks.size(); i++) {
				
				boolean hasHappened = false;

				float[] dist = new float[1];
				Location.distanceBetween(
						bookmarks.get(i).getPosition().latitude,
						bookmarks.get(i).getPosition().longitude, cLatitude,
						cLongitude, dist);
				

				if (dist[0] < bookmarks.get(i).getRadius() && !hasHappened) {
					// Wifi
					if (bookmarks.get(i).getWifiState().state == R.id.wifi_on_radio) {
						wifiChanger(true);

					} else if (bookmarks.get(i).getWifiState().state == R.id.wifi_off_radio) {
						wifiChanger(false);
					}

					// Bluetooth
					if (bookmarks.get(i).getBluetoothState().state == R.id.bluetooth_on_radio) {
						bluetoothChanger(true);
					} else if (bookmarks.get(i).getBluetoothState().state == R.id.bluetooth_off_radio) {
						bluetoothChanger(false);
					}

					// Mute
					if (bookmarks.get(i).getMuteState().state == R.id.mute_on_radio) {
						audioChanger(true);
					} else if (bookmarks.get(i).getMuteState().state == R.id.mute_off_radio) {
						audioChanger(false);
					}

					// Vibrator
					if (bookmarks.get(i).getVibratorState().state == R.id.vibrator_on_radio) {
						vibratorChanger(true);
					} else if (bookmarks.get(i).getVibratorState().state == R.id.vibrator_off_radio) {
						vibratorChanger(false);
					}

					// Airplane mode
					if (bookmarks.get(i).getFlightmodeState().state == 0) {
						flightmodeChanger(true);
					} else if (bookmarks.get(i).getFlightmodeState().state == 1) {
						flightmodeChanger(false);
					}

					hasHappened = true;

				} else if (hasHappened
						&& dist[0] > bookmarks.get(i).getRadius()) {
					// Wifi
					if (bookmarks.get(i).getWifiState().state == R.id.wifi_off_radio
							&& bookmarks.get(i).getWifiState().revertOnExit) {
						wifiChanger(false);
					} else if (bookmarks.get(i).getWifiState().state == R.id.wifi_on_radio
							&& bookmarks.get(i).getWifiState().revertOnExit) {
						wifiChanger(true);
					}

					// Bluetooth
					if (bookmarks.get(i).getBluetoothState().state == R.id.bluetooth_off_radio
							&& bookmarks.get(i).getBluetoothState().revertOnExit) {
						bluetoothChanger(false);
					} else if (bookmarks.get(i).getBluetoothState().state == R.id.bluetooth_on_radio
							&& bookmarks.get(i).getBluetoothState().revertOnExit) {
						bluetoothChanger(true);
					}

					// Mute
					if (bookmarks.get(i).getMuteState().state == R.id.mute_off_radio
							&& bookmarks.get(i).getMuteState().revertOnExit) {
						audioChanger(false);
					} else if (bookmarks.get(i).getMuteState().state == R.id.mute_on_radio
							&& bookmarks.get(i).getMuteState().revertOnExit) {
						audioChanger(true);
					}

					// Vibrator
					if (bookmarks.get(i).getVibratorState().state == R.id.vibrator_off_radio
							&& bookmarks.get(i).getVibratorState().revertOnExit) {
						vibratorChanger(false);
					} else if (bookmarks.get(i).getVibratorState().state == R.id.vibrator_on_radio
							&& bookmarks.get(i).getVibratorState().revertOnExit) {
						vibratorChanger(true);
					}

					// Airplane mode
					if (bookmarks.get(i).getFlightmodeState().state == R.id.flightmode_off_radio
							&& bookmarks.get(i).getFlightmodeState().revertOnExit) {
						flightmodeChanger(false);
					} else if (bookmarks.get(i).getFlightmodeState().state == R.id.flightmode_on_radio
							&& bookmarks.get(i).getFlightmodeState().revertOnExit) {
						flightmodeChanger(true);
					}

					hasHappened = false;
				}

			}
		}

	}

	// Copy all the underneath

	public void wifiChanger(boolean enable_or_disable) {

		if (enable_or_disable) {
			wifiManager.setWifiEnabled(true);
		}
		if (!enable_or_disable) {
			wifiManager.setWifiEnabled(false);
		}
	}

	public void bluetoothChanger(boolean enable_or_disable) {

		if (enable_or_disable) {

		} else if (!enable_or_disable) {

		}
	}

	public void vibratorChanger(boolean enable_or_disable) {
		if (enable_or_disable) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		} else if (!enable_or_disable) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
	}

	public void audioChanger(boolean enable_or_disable) {
		if (enable_or_disable) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vib.cancel();
		}

		if (!enable_or_disable) {
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
	}

	public void flightmodeChanger(boolean enable_or_disable) {
		if (enable_or_disable
				&& Settings.System.getInt(this.getContentResolver(),
						Settings.System.AIRPLANE_MODE_ON, 0) != 1) {
			Settings.System.putInt(this.getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 1);
		} else if (enable_or_disable
				&& Settings.System.getInt(this.getContentResolver(),
						Settings.System.AIRPLANE_MODE_ON, 0) == 1) {
			Settings.System.putInt(this.getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 0);
		}
	}
}
