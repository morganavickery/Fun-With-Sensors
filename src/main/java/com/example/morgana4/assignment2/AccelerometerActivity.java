package com.example.morgana4.assignment2;

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
import android.widget.ImageView;

import java.util.ArrayList;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sm;
    private Sensor acc;
//    public static ArrayList<Double> valueList;
//    public ArrayList<Double> calcList;
    public static double[] valueGraph;
    public static double[] sdList;
    public static double[] meanList;
    public double mean;
    public double standardDev;
    public double value;
    Button anim;
    AccGraph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sm = ChooserActivity.sm;
        acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);

        //initialization
//        valueList = new ArrayList<Double>();
        valueGraph = new double[3];
        meanList = new double[3];
        sdList = new double[3];
        graph = new AccGraph(this);
//        calcList = new ArrayList<Double>();

        graph = (AccGraph) findViewById(R.id.accGraph);
        anim = (Button)findViewById(R.id.animRun);

//        valueList.add(0.0);
//        valueList.add(0.0);
//        valueList.add(0.0);
        meanList[0] = 0;
        meanList[1] = 0;
        meanList[2] = 0;
        valueGraph[0] = 0;
        valueGraph[1] = 0;
        valueGraph[2] = 0;
        sdList[0] = 0;
        sdList[1] = 0;
        sdList[2] = 0;
    }

    public void swapHome(View v){
        Intent intent = new Intent(this, ChooserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float xVal = sensorEvent.values[0];
        float yVal = sensorEvent.values[1];
        float zVal = sensorEvent.values[2];

        value = Math.sqrt((xVal*xVal)+(yVal*yVal)+(zVal*zVal));
        Log.v("TAG", "current acceleration is:" + value);

        //updating value array/graph
//        valueList.add(value);
//        calcList.add(value);
        double temp5 = valueGraph[0];
        double temp6 = valueGraph[1];
        valueGraph[0] = value;
        valueGraph[1] = temp5;
        valueGraph[2] = temp6;

        Log.v("TAG", "current acc is" + value);

        //updating mean array
        mean = findMean(valueGraph);
        double temp1 = meanList[0];
        double temp2 = meanList[1];
        meanList[0] = mean;
        meanList[1] = temp1;
        meanList[2] = temp2;
        Log.v("TAG", "current mean is" + mean);

        //updating sdArray
        standardDev = findDeviation(valueGraph);
        double temp3 = sdList[0];
        double temp4 = sdList[1];
        sdList[0] = standardDev;
        sdList[1] = temp3;
        sdList[2] = temp4;
        Log.v("TAG", "current sd is" + standardDev);

        graph.set_meanList(meanList);
        graph.set_valueList(valueGraph);
        graph.set_sdList(sdList);
        Log.v("TAG", "graphs have been updated/coordinated");

        graph.invalidate();
        Log.v("TAG", "graph should be invalidating");

        if(value > 12){
            anim.setBackgroundResource(R.drawable.run);
        } else {
            anim.setBackgroundResource(R.drawable.walk);
        }

    }

    public double findMean(double[] a){
        double sum = 0;
        double mean;
        for(int i = 0; i<a.length; i++){
            sum = a[i] + sum;
        }
        mean = sum/a.length;
        return mean;
    }

    public double findDeviation(double[] a) {
        double mean = findMean(a);
        double squareSum = 0;
        for (int i = 0; i < a.length; i++) {
            squareSum += Math.pow(a[i] - mean, 2);
        }
        return Math.sqrt((squareSum) / (a.length));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
