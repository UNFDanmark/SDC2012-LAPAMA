package dk.unf.sdc.gruppec.data;

public class Bookmark {
	private String name;
	private Coordinate position;
	private int radius;
	private boolean revertToDefault;
	private State wifiState;
	private State mobileNetworkState;
	private State bluetoothState;
	private State muteState;
	private State vibratorState;
	private State flightmodeState;
	private int id;

	public Bookmark(String title, Coordinate location, int rad,
			boolean revertToDef, State wifi_State, State mobileNetwork_State,
			State bluetooth_State, State mute_State, State vibrator_State,
			State flightmode_State) {
		name = title;
		position = location;
		radius = rad;
		revertToDefault = revertToDef;
		wifiState = wifi_State;
		mobileNetworkState = mobileNetwork_State;
		bluetoothState = bluetooth_State;
		muteState = mute_State;
		vibratorState = vibrator_State;
		flightmodeState = flightmode_State;
	}
	
	public Bookmark(int n_id, String title, Coordinate location, int rad,
			boolean revertToDef, State wifi_State, State mobileNetwork_State,
			State bluetooth_State, State mute_State, State vibrator_State,
			State flightmode_State) {
		id = n_id;
		name = title;
		position = location;
		radius = rad;
		revertToDefault = revertToDef;
		wifiState = wifi_State;
		mobileNetworkState = mobileNetwork_State;
		bluetoothState = bluetooth_State;
		muteState = mute_State;
		vibratorState = vibrator_State;
		flightmodeState = flightmode_State;
	}

	public Bookmark(String title, double latitude, double longitude, int rad,
			boolean revertToDef, State wifi_State, State mobileNetwork_State,
			State bluetooth_State, State mute_State, State vibrator_State,
			State flightmode_State) {
		name = title;
		position = new Coordinate(latitude, longitude);
		radius = rad;
		revertToDefault = revertToDef;
		wifiState = wifi_State;
		mobileNetworkState = mobileNetwork_State;
		bluetoothState = bluetooth_State;
		muteState = mute_State;
		vibratorState = vibrator_State;
		flightmodeState = flightmode_State;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isRevertToDefault() {
		return revertToDefault;
	}

	public void setRevertToDefault(boolean revertToDefault) {
		this.revertToDefault = revertToDefault;
	}

	public State getWifiState() {
		return wifiState;
	}

	public void setWifiState(State wifiState) {
		this.wifiState = wifiState;
	}

	public State getMobileNetworkState() {
		return mobileNetworkState;
	}

	public void setMobileNetworkState(State mobileNetworkState) {
		this.mobileNetworkState = mobileNetworkState;
	}

	public State getBluetoothState() {
		return bluetoothState;
	}

	public void setBluetoothState(State bluetoothState) {
		this.bluetoothState = bluetoothState;
	}

	public State getMuteState() {
		return muteState;
	}

	public void setMuteState(State muteState) {
		this.muteState = muteState;
	}

	public State getVibratorState() {
		return vibratorState;
	}

	public void setVibratorState(State vibratorState) {
		this.vibratorState = vibratorState;
	}

	public State getFlightmodeState() {
		return flightmodeState;
	}

	public void setFlightmodeState(State flightmodeState) {
		this.flightmodeState = flightmodeState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
