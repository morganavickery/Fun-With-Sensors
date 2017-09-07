package com.example.morgana4.assignment2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOError;
import java.util.ArrayList;

/**
 * Created by morgana4 on 2/28/17.
 */

public class AccGraph extends View {

    double[] _meanList;
    double[] _valueList;
    double[] _sdList;

    public AccGraph(Context context) {
        super(context);

        _meanList = new double[3];
        _valueList = new double[3];
        _sdList = new double[3];

    }

    public AccGraph(Context context, AttributeSet attrs){
        super(context, attrs);

        _meanList = new double[3];
        _valueList = new double[3];
        _sdList = new double[3];

    }

    public void set_meanList(double[] d){
        _meanList = d;
    }

    public void set_valueList(double[] d){
        _valueList = d;
    }

    public void set_sdList(double[] d){
        _sdList = d;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //PAINTS
        Paint red = new Paint();
        red.setColor(Color.RED);
        Paint yellow = new Paint();
        yellow.setColor(Color.GREEN);
        Paint green = new Paint();
        green.setColor(Color.BLUE);
        Paint grey = new Paint();
        grey.setColor(Color.GRAY);

        //compensate for fucked up pixel scale
        float valueA = 600-(((float)_valueList[0]* 600/15));
        float valueB = 600-(((float)_valueList[1]* 600/15));
        float valueC = 600-(((float)_valueList[2]* 600/15));

        float meanA = 600-((float)_meanList[0]* 600/15);
        float meanB = 600-((float)_meanList[1]* 600/15);
        float meanC = 600-((float)_meanList[2]* 600/15);

        float sdA = 600-((float)_sdList[0]* 600/15);
        float sdB = 600-((float)_sdList[1]* 600/15);
        float sdC = 600-((float)_sdList[2]* 600/15);

        //graph shit
        canvas.drawLine(0,0,600,0,grey);
        canvas.drawLine(0,40,600,40,grey);
        canvas.drawLine(0,80,600,80,grey);
        canvas.drawLine(0,120,600,120,grey);
        canvas.drawLine(0,160,600,160,grey);
        canvas.drawLine(0,200,600,200,grey);
        canvas.drawLine(0,240,600,240,grey);
        canvas.drawLine(0,280,600,280,grey);
        canvas.drawLine(0,320,600,320,grey);
        canvas.drawLine(0,360,600,360,grey);
        canvas.drawLine(0,400,600,400,grey);
        canvas.drawLine(0,440,600,440,grey);
        canvas.drawLine(0,480,600,480,grey);
        canvas.drawLine(0,520,600,520,grey);
        canvas.drawLine(0,560,600,560,grey);
        canvas.drawLine(0,599,600,599,grey);

        canvas.drawLine(0,0,0,600,grey);
        canvas.drawLine(300,0,300,600,grey);
        canvas.drawLine(599,0,600,599,grey);




        //DRAW VALUE
        canvas.drawLine(0,valueA,300,valueB,red);
        canvas.drawLine(300,valueB,600,valueC,red);
        canvas.drawCircle(0,valueA,5,red);
        canvas.drawCircle(300,valueB,5,red);
        canvas.drawCircle(600,valueC,5,red);

        //DRAW MEAN
        canvas.drawLine(0,meanA,300,meanB,yellow);
        canvas.drawLine(300,meanB,600,meanC,yellow);
        canvas.drawCircle(0,meanA,5,yellow);
        canvas.drawCircle(300,meanB,5,yellow);
        canvas.drawCircle(600,meanC,5,yellow);

        //DRAW STDV
        canvas.drawLine(0,sdA,300,sdB,green);
        canvas.drawLine(300,sdB,600,sdC,green);
        canvas.drawCircle(0,sdA,5,green);
        canvas.drawCircle(300,sdB,5,green);
        canvas.drawCircle(600,sdC,5,green);
    }
}
