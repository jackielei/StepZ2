package my_util;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.List;

import data_structure.WeatherContent;

public class GetWeather {
	private final String URLSTRING = "http://api.wunderground.com/api/f901f674e46d96d2/forecast10day/lang:JP/q/Japan/Tokyo.json";
	private StringBuilder jsonData;
	private BufferedReader readData;

	public void getConnect() {
		HttpURLConnection connection = null;
	}

	public List<WeatherContent> getWeatherContent() {
	}
}
