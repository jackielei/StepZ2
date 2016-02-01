package window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import my_util.GetWeather;

public class MainWindow extends JFrame{
	private JList<String> mJList_weather;
	private JButton mJButton_show;
	private JLabel mJLabel_time;

	public MainWindow() {
		super("Weatherforecast");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(700, 700);

		// データ取得時刻を表示する
		mJLabel_time = new JLabel("");
		super.getContentPane().add(mJLabel_time, BorderLayout.NORTH);

		// 取得したデータを表示するリスト
		mJList_weather = new JList<>();
		super.getContentPane().add(mJList_weather, BorderLayout.CENTER);

		// データを取得する
		mJButton_show = new JButton("get info");
		super.getContentPane().add(mJButton_show, BorderLayout.SOUTH);
		ActionListener press_bnt_show = new GetInformation();
		mJButton_show.addActionListener(press_bnt_show);
	}

	class GetInformation implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == mJButton_show) {
				GetWeather myWeather = GetWeather.getInstance();
				mJList_weather.setListData(myWeather.getWeathercastList());
				mJLabel_time.setText(myWeather.getGetDate());
			}
		}
	}
}