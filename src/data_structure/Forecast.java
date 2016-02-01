package data_structure;

public class Forecast {
	private TxtForecast mForecast;  // forecast の中身

	//------------------
	// setter
	//------------------
	public void setForecast(TxtForecast forecast) { mForecast = forecast; }

	//------------------
	// getter
	//------------------
	public TxtForecast getForecast() { return mForecast; }
}
