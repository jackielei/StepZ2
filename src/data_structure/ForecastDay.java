package data_structure;

import java.util.List;

public class ForecastDay {
	/**
	 * 予報取得時間
	 */
	private String mDate;

	private List<WeatherContent> mForecastday;  // forecast配列

	//------------------
	// setter
	//------------------
	public void setDate(String date) { mDate = date; }
	public void setForecastday(List<WeatherContent> forecast) { mForecastday = forecast; }

	//------------------
	// getter
	//------------------
	/**
	 * 予報取得時間を取得する
	 * @return 取得時間
	 */
	public String getDate() { return mDate; }
	/**
	 * forecastday structureを返す
	 * @return forecastday structure
	 */
	public List<WeatherContent> getForecastday() { return mForecastday; }
}
