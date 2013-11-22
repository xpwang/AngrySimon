package edu.iastate.angrysimon;


import android.view.GestureDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class Simon_Settings_Screen extends CustomGestureListener {

	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_settings_screen);
        
        super.setGestureDetector(new GestureDetector(this.getApplicationContext(), this));
     	super.setLeftRight(Simon_Main_Screen.class, Simon_Main_Screen.class);   
   	       
    
	}
	
	
	
	
}
