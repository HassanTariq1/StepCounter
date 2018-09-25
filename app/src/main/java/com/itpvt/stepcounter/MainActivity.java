package com.itpvt.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {
    SensorManager sensor;
    TextView tv_steps;

boolean run= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


tv_steps=(TextView) findViewById(R.id.infos);

 sensor=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        

    }

    @Override
    protected void onResume() {
        super.onResume();
        run=true;
        Sensor  cs=sensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(cs!=null){
            sensor.registerListener(this, cs,   SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(getApplicationContext(),"Sensor not",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        run=false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(run){
            tv_steps.setText(String.valueOf(sensorEvent.values[0]));

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
