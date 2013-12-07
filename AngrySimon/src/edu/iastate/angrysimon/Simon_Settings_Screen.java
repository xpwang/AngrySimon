package edu.iastate.angrysimon;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class Simon_Settings_Screen extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager()
				.beginTransaction()
				.replace(android.R.id.content, new AngrySimonSettingsFragment())
				.commit();

		SharedPreferences GamePrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		Resources res = getResources();

		// boolean sound;
		// sound = GamePrefs.getBoolean(res.getString(R.string.), true);

	}

	public static class AngrySimonSettingsFragment extends PreferenceFragment {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			PreferenceManager.setDefaultValues(getActivity(),
					R.xml.angry_simon_game_settings, false);
			addPreferencesFromResource(R.xml.angry_simon_game_settings);

		}

	}
}
