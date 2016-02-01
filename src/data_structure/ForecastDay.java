package data_structure;

import java.util.List;

public class ForecastDay {
	/**
	 * 予報取得時間
	 */
	private String date;

	private List<WeatherContent> forecastday;  // forecast配列

	//------------------
	// setter
	//------------------
	public void setDate(String date) { this.date = date; }
	public void setForecastday(List<WeatherContent> forecastday) { this.forecastday = forecastday; }

	//------------------
	// getter
	//------------------
	/**
	 * 予報取得時間を取得する
	 * @return 取得時間
	 */
	public String getDate() { return date; }
	/**
	 * forecastday structureを返す
	 * @return forecastday structure
	 */
	public List<WeatherContent> getForecastday() { return this.forecastday; }
}
