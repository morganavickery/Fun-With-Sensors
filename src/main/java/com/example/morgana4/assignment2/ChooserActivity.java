package com.example.morgana4.assignment2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.morgana4.assignment2.R.id.acc_button;
import static com.example.morgana4.assignment2.R.id.acc_info;
import static com.example.morgana4.assignment2.R.id.acc_status;
import static com.example.morgana4.assignment2.R.id.light_button;
import static com.example.morgana4.assignment2.R.id.light_info;
import static com.example.morgana4.assignment2.R.id.light_status;

public class ChooserActivity extends AppCompatActivity implements SensorEventListener{

    //FIELDS//
    public static SensorManager sm;
    private Sensor acc;
    private Sensor light;

    //ON CREATE//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        Button accButton = (Button) findViewById(acc_button);
        Button lightButton = (Button) findViewById(light_button);
        TextView aStatus = (TextView) findViewById(acc_status);
        TextView lStatus = (TextView) findViewById(light_status);
        TextView aInfo = (TextView) findViewById(acc_info);
        TextView lInfo = (TextView) findViewById(light_info);


        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //displaying status/info of each sensor//
        if (sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            aStatus.setText("Status: Accelerometer is Present");
            acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener(this, acc, 1000000);
            float range = acc.getMaximumRange();
            float res = acc.getResolution();
            float delay = acc.getMinDelay();
            lInfo.setText("Info: Range - " + range + ", Res. - " + res + ", Delay - " + delay);
        } else {
            aStatus.setText("Status: Accelerometer Sensor is Not Present");
        }

        if (sm.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            lStatus.setText("Status: Light Sensor is Present");
            light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
            sm.registerListener(this, light, 1000000);
            float range = light.getMaximumRange();
            float res = light.getResolution();
            float delay = light.getMinDelay();
            aInfo.setText("Info: Range - " + range + ", Res. - " + res + ", Delay - " + delay);

        } else {
            lStatus.setText("Status: Light Sensor is Not Present");
        }


    }

    @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

//SWAPPING ACTIVITIES//
    public void swapAcc(View v){
        Log.v("TAG", "swapped to accelerometer");
        Intent intent = new Intent(this, AccelerometerActivity.class);
        startActivity(intent);
    }

    public void swapLight(View v){
        Log.v("TAG", "swapped to light");
        Intent intent = new Intent(this, LightActivity.class);
        startActivity(intent);
    }
}
