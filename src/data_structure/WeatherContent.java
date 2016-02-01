/**
 * wunderground.comから取得したjsonの最下層にあるデータストラクチャ
 */

package data_structure;

import com.google.gson.annotations.SerializedName;

public class WeatherContent {
	/**
	 * 予報日時
	 */
	private String mTitle;
	/**
	 * 予報内容
	 */
	@SerializedName("fcttext_metric")
	private String mWeathercast;  // 予報を格納するためのもの

	//------------------
	// setter
	//------------------
	public void setTitle(String title) { mTitle = title; }
	public void setWeathercast(String weathercast) { mWeathercast = weathercast; }

	//------------------
	// getter
	//------------------
	/**
	 * 予報日時を取得
	 * @return 予報日時
	 */
	public String getTitle() { return mTitle; }
	/**
	 * 予報内容を取得
	 * @return 予報内容
	 */
	public String getWeathercast() { return mWeathercast; }

}
