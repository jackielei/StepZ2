package my_util;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import data_structure.WeatherContent;

public class GetWeather {
	private final String URLSTRING = "http://api.wunderground.com/api/f901f674e46d96d2/forecast10day/lang:JP/q/Japan/Tokyo.json";
	private StringBuilder mJsonData;
	private BufferedReader mReadData;
	HttpURLConnection mConnection;

	public void getConnect() {
		mConnection = null;
		try {
			URL url = new URL(URLSTRING);
			mConnection = (HttpURLConnection) url.openConnection();
			mConnection .setRequestMethod("GET");
			mConnection .setInstanceFollowRedirects(false);
			mConnection .connect();
		} catch (Exception exception) { exception.printStackTrace(); }
	}

	public List<WeatherContent> getWeatherContent() {
	}
}
