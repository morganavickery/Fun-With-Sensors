package com.example.morgana4.assignment2;

import android.content.Intent;
import android.graphics.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.Policy;
import java.util.ArrayList;

public class LightActivity extends AppCompatActivity implements SensorEventListener{

    private Sensor light;
    public SensorManager sm;
//    public  ArrayList<Double> valueList;
//    public  ArrayList<Double> calcList;
    public  static double[] valueGraph;
    public  static double[] sdList;
    public  static double[] meanList;
    public double mean;
    public double standardDev;
    public double val;
    LightGraph graph;
    Button anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        sm = ChooserActivity.sm;
        light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);

    //initialization of fields
//        valueList = new ArrayList<Double>();
        valueGraph = new double[3];
        meanList = new double[3];
        sdList = new double[3];
//        calcList = new ArrayList<Double>();

        graph = (LightGraph) findViewById(R.id.lightGraph);
        anim = (Button) findViewById(R.id.animLight);

        //initializing first 3 in each list
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
        val = sensorEvent.values[0];

//        valueList.add(val);
//        calcList.add(val);

        valueGraph[0] = val;
        double temp5 = valueGraph[0];
        double temp6 = valueGraph[1];
        valueGraph[0] = mean;
        valueGraph[1] = temp5;
        valueGraph[2] = temp6;
        Log.v("TAG", "current light is:" + val);

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
        Log.v("TAG", "current SD is" + standardDev);

        graph.set_meanList(meanList);
        graph.set_valueList(valueGraph);
        graph.set_sdList(sdList);
        Log.v("TAG", "graphs have been updated/coordinated");

        graph.invalidate();
        Log.v("TAG", "graph should be invalidating");

        if(val > 8.0){
            anim.setBackgroundResource(R.drawable.on);
        } else {
            anim.setBackgroundResource(R.drawable.off);
        }
    }

    public double findMean(double[] a){
        double sum = 0;
        double mean;
        for(int i = 0; i<a.length; i++){
            sum = a[i] + sum;
        }
        mean = sum/(a.length);
        return mean;
    }

    public double findDeviation(double[] a) {
        double mean = findMean(a);
        double squareSum = 0;
        for (int i = 0; i < a.length; i++) {
            squareSum += Math.pow(a[i] - mean, 2);
        }
        return Math.sqrt((squareSum) / (a.length - 1));
    }

//    public double findSD(ArrayList<Double> a){
//        double currentSum = 0;
//        for(int i = 0; i<a.size(); i++){
//            currentSum = a.get(i) + currentSum;
//        }
//        double currentMean = currentSum/(a.size()-3);
//        Double[] tempDiffs = new Double[a.size()];
//        for(int i = 0; i<a.size(); i++){
//            tempDiffs[i] = a.get(i) - currentMean;
//            tempDiffs[i] = tempDiffs[i] * tempDiffs[i];
//        }
//        double stdv;
//        double tempSum = 0;
//        for(int i = 0; i<tempDiffs.length;i++){
//            tempSum = tempSum + tempDiffs[i];
//        }
//        stdv = tempSum/(tempDiffs.length-3);
//        stdv = Math.sqrt(stdv);
//        return stdv;
//    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
