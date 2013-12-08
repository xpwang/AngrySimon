package edu.iastate.scoreboard;

import java.util.List;

import edu.iastate.angrysimon.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ScoreboardActivity extends Activity{

	private TextView tv;
	private ScoreboardDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoreboard);
		tv = (TextView) findViewById(R.id.textView2);
		datasource = ScoreboardDataSource.getInstance(this);
		datasource.open();
		
		List<Score> list = datasource.getAllScores();
		String str = "";
		for(int i=0; i<list.size(); i++)
		{
			str = str + "ID: " + list.get(i).getId() + " Name: " + list.get(i).getUsername() + " Score: " +list.get(i).getScore()  + " Time: " +list.get(i).getTime() + " Mode: " + list.get(i).getMode() + "\n";
		}
		
		tv.setText(str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scoreboard, menu);
		return true;
	}

}
