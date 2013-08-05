package com.ebakyt.androidsensors.proximity;

import com.ebakyt.androidsensors.R;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ProximityMain extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
    private Sensor mProximity;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proximity);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(this, mProximity , SensorManager.SENSOR_DELAY_NORMAL);

        Button btProxiGraph = (Button) findViewById(R.id.btnProximityGraph);
        btProxiGraph.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ProximityMain.this, ProximityGraph.class));
			}
		});
	}
    
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		///
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		
		TextView tvX= (TextView)findViewById(R.id.textViewX);
		float x = event.values[0];
		tvX.setText(Float.toString(x));
	}
}
