package edu.iastate.angrysimon;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class SimonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simon, menu);
        return true;
    }
    
    public void redOnClick(View view){
    	Toast t = Toast.makeText(getApplicationContext(), "ID: " + view.getId(), Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void blueOnClick(View view){
    	Toast t = Toast.makeText(getApplicationContext(), "ID: " + view.getId(), Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void greenOnClick(View view){
    	Toast t = Toast.makeText(getApplicationContext(), "ID: " + view.getId(), Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void blackOnClick(View view){
    	Toast t = Toast.makeText(getApplicationContext(), "ID: " + view.getId(), Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void yellowOnClick(View view){
    	Toast t = Toast.makeText(getApplicationContext(), "ID: " + view.getId(), Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void pinkOnClick(View view){
    	Toast t = Toast.makeText(getApplicationContext(), "ID: " + view.getId(), Toast.LENGTH_SHORT);
    	t.show();
    }
}
