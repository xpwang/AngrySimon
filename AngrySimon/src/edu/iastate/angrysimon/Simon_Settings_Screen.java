package edu.iastate.angrysimon;


import java.sql.PreparedStatement;

import android.view.GestureDetector;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class Simon_Settings_Screen extends PreferenceActivity {

	
	

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        getFragmentManager().beginTransaction().replace(android.R.id.content, new AngrySimonSettingsFragment()).commit();  
       
        SharedPreferences GamePrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Resources res = getResources();
       
     //   boolean sound;
     //   sound = GamePrefs.getBoolean(res.getString(R.string.), true); 
       	  
      
	}
	

	public static class AngrySimonSettingsFragment extends PreferenceFragment{
		
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			PreferenceManager.setDefaultValues(getActivity(), R.xml.angry_simon_game_settings, false);
		    addPreferencesFromResource(R.xml.angry_simon_game_settings);
	
		
		
		
		}
	
		
	}
}

