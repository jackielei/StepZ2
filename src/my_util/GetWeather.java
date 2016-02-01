package my_util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import data_structure.Forecast;
import data_structure.WeatherContent;

public class GetWeather {
	private static GetWeather theInstance;

	private final String URLSTRING = "http://api.wunderground.com/api/f901f674e46d96d2/forecast10day/lang:JP/q/Japan/Tokyo.json";
	private StringBuilder mJsonData;
	private BufferedReader mReadData;
	HttpURLConnection mConnection;

	/**
	 * create a instance
	 */
	private GetWeather() {};
	public static GetWeather getInstance() {
		if (theInstance == null)
			theInstance = new GetWeather();

		return theInstance;
	}

	/**
	 * connect to the URL
	 */
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

	/**
	 * get a json data
	 */
	public void getJson() {
		try {
			mReadData = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
			String myWeather = mReadData.readLine();
			while(myWeather != null) {
				mJsonData.append(myWeather);
				myWeather = mReadData.readLine();
			}
		} catch (Exception exception) { exception.printStackTrace(); }
	}

	/**
	 * parse the json data that get by getJson()
	 *
	 * @return weather info and the time
	 */
	public List<WeatherContent> getWeather() {
		WeatherContent tempWeather = new WeatherContent();
		List<WeatherContent>listOfWeather = new ArrayList<>();
		Gson gson = new Gson();

		Forecast weather = gson.fromJson(mJsonData.toString(), Forecast.class);
		for (WeatherContent weatherContent : weather.getForecast().getTxt_forecast().getForecastday()) {
			tempWeather.setTitle(weatherContent.getTitle());
			tempWeather.setWeathercast(weatherContent.getWeathercast());
			listOfWeather.add(tempWeather);
		}

		return listOfWeather;
	}
}
