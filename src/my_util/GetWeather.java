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
	private String mGetDate;
	private HttpURLConnection mConnection;

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
	private void getConnect() {
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
	private void getJson() {
		try {
			mJsonData = new StringBuilder();
			mReadData = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
			String myWeather = mReadData.readLine();
			while(myWeather != null) {
				mJsonData.append(myWeather);
				myWeather = mReadData.readLine();
			}
			//System.out.println(mJsonData.toString());
		} catch (Exception exception) { exception.printStackTrace(); }
	}

	/**
	 * parse the json data that get by getJson()
	 *
	 * @return weather info and the time
	 */
	public List<WeatherContent> getWeather() {
		List<WeatherContent>listOfWeather = new ArrayList<>();
		Gson gson = new Gson();
		try {
			Forecast weather = gson.fromJson(mJsonData.toString(), Forecast.class);
			mGetDate = weather.getForecast().getTxt_forecast().getDate();
			System.out.println(mGetDate);
			for (WeatherContent weatherContent : weather.getForecast().getTxt_forecast().getForecastday())
				listOfWeather.add(weatherContent);

		} catch (Exception exception) { exception.printStackTrace(); }

		return listOfWeather;
	}

	/**
	 * 接続から予報取得まで一発で行う
	 */
	public String[] getWeathercastList() {
		getConnect();
		getJson();
		return getWeathercast();
	}

	/**
	 * 情報取得時刻を取得
	 *
	 * @return 情報取得時刻
	 */
	public String getGetDate() {
		System.out.println(mGetDate);
		return mGetDate;
	}

	/**
	 * 予報日のListをStringのArrayに変換
	 *
	 * @param myList : 変換対象
	 * @return 変換後の予報日配列
	 */
	public String[] getDateString(List<WeatherContent> myList) {
		String[] myDate = new String[myList.size()];
		WeatherContent content = new WeatherContent();

		for (int i = 0; i < myList.size(); i++) {
			content = myList.get(i);
			myDate[i] = content.getTitle();
		}

		return myDate;
	}

	public String[] getDateString() { return getDateString(getWeather()); }

	/**
	 * 予報内容のListをStringのArrayに変換
	 *
	 * @param myList : 変換対象
	 * @return 変換後の予報内容配列
	 */
	public String[] getWeatherString(List<WeatherContent> myList) {
		String[] myWeather = new String[myList.size()];
		WeatherContent content = new WeatherContent();

		for (int i = 0; i < myList.size(); i++) {
			content = myList.get(i);
			myWeather[i] = content.getWeathercast();
		}

		return myWeather;
	}

	public String[] getWeatherString() { return getWeatherString(getWeather()); }

	/**
	 * 変換した予報日付と予報内容の配列を結合
	 *
	 * @param myList : 対象となる予報のList
	 * @return 予報の配列
	 */
	public String[] getWeathercast (List<WeatherContent> myList) {
		return mergeStringArray(getDateString(myList), getWeatherString(myList));
	}

	public String[] getWeathercast() { return getWeathercast(getWeather()); }

	/**
	 * 文字列を結合するユーティリティ
	 *
	 * @param string1 : 結合対象その1
	 * @param string2 : 結合対象その2
	 * @return 結合された配列
	 */
	public String[] mergeStringArray(String[] string1, String[] string2) {
		String[] strings = new String[string1.length];
		for (int i = 0; i < strings.length; i++)
			strings[i] = String.format("日時: %-15s 天気: %-10s", string1[i], string2[i]);

		return strings;
	}
}
