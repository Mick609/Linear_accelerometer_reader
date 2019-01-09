package com.example.mick.hello_world;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private SensorEventListener mSensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = null;
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                SensorEventReceived(sensorEvent);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null) {
            Log.i("TAG", "Linear Accelerometer Exists.");
            registerLinearAccelerometer();
        } else {
            Log.i("TAG", "Linear Accelerometer Does not Exist.");
        }

    }

    private void registerLinearAccelerometer() {
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
//        50Hz
        mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void SensorEventReceived(SensorEvent sensorEvent) {
        String sensorRead = "Timestamp: " + sensorEvent.timestamp + "\n"
                + "X: " + sensorEvent.values[0] + "\n"
                + "Y: " + sensorEvent.values[1] + "\n"
                + "Z: " + sensorEvent.values[2];

        mTextView.setText(sensorRead);
    }
}
