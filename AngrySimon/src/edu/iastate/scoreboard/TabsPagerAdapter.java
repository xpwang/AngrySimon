package edu.iastate.scoreboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			Fragment fragment = new ClassicFragment();
			return fragment;
		case 1:
			Fragment angry = new AngryFragment();

			return angry;
		case 2:
			Fragment rage = new RageFragment();
			return rage;
		}

		return null;

	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "OBJECT " + (position + 1);
	}
}
