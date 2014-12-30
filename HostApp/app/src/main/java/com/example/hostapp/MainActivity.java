package com.example.hostapp;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements ScrimInsetsFrameLayout.OnInsetsCallback{
	Toolbar toolbar;
	private Resources res;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		res = this.getResources();

		this.setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
	    { 
			ScrimInsetsFrameLayout scrimInsetsFrameLayout = (ScrimInsetsFrameLayout)
	                findViewById(R.id.linearLayout);
	        scrimInsetsFrameLayout.setOnInsetsCallback(this);
	    } 

	}

	private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            return new IndexFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "tab 1";
                case 1: return "tab 2";
                case 2: return "tab 3";
                case 3: return "tab 4";
                default: return null;
            }
        }

    }

    /*
     * It doesn't matter the color of the icons, but they must have solid colors
     */
    private Drawable getIcon(int position) {
        switch(position) {
            case 0:
                return res.getDrawable(R.drawable.ic_drawer);
            case 1:
                return res.getDrawable(R.drawable.ic_drawer);
            case 2:
                return res.getDrawable(R.drawable.ic_drawer);

        }
        return null;
    }

    public static class  IndexFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            TextView text = new TextView(container.getContext());
            text.setText("Section");
            text.setGravity(Gravity.CENTER);
            return text;
        }
    }

	@Override
	public void onInsetsChanged(Rect insets) {
		  Toolbar toolbar = this.toolbar;
	        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)
	                toolbar.getLayoutParams();
	        lp.topMargin = insets.top;
	        int top = insets.top;
	        insets.top += toolbar.getHeight();
	        toolbar.setLayoutParams(lp);
	      //  mMapFragment.setMapInsets(insets);
	        insets.top = top; // revert
	}

}
