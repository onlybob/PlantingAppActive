package com.openatk.tillage;

import java.util.Date;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.openatk.tillage.db.DatabaseHelper;
import com.openatk.tillage.db.Field;
import com.openatk.tillage.drawing.MyPolygon;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class FragmentSlider extends Fragment implements OnTouchListener {
	
	LayoutInflater vi;
	SliderListener listener = null;
	
	// Interface for receiving data
	public interface SliderListener {
		public void SliderDragDown(int start);
		public void SliderDragDragging(int whereY);
		public void SliderDragUp(int whereY);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_slider, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//listener.SliderRequestData();
	}

	public void populateData() {
	
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof SliderListener) {
			listener = (SliderListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement FragmentSlider.SliderListener");
		}
		Log.d("FragmentSlider", "Attached");
	}

	public int getHeight() {
		// Method so close transition can work
		return getView().getHeight();
	}
	

	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float eventY = event.getRawY();
		
		switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
            	listener.SliderDragDown((int)eventY);
               break; 
            }
            case MotionEvent.ACTION_UP:
            {     
            	listener.SliderDragUp((int)(eventY));
                 break;
            }
            case MotionEvent.ACTION_MOVE:
            {
            	listener.SliderDragDragging((int)(eventY));
                break;
            }
        }
        return true;
	}

}