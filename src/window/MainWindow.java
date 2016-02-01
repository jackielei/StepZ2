package window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class MainWindow extends JFrame{
	private JLabel mJLabel_date;
	private JList<String> mJList_weather;
	private JButton mJButton_show;

	public MainWindow() {
		super("Weatherforecast");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(700, 700);

		// データ取得時刻を表示するラベル
		mJLabel_date = new JLabel("");
		super.getContentPane().add(mJLabel_date, BorderLayout.NORTH);

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
			if (event.getSource() == mJButton_show)
				getWeather();
		}
	}
}