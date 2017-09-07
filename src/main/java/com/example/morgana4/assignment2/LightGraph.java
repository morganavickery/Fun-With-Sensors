package com.example.morgana4.assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import static com.example.morgana4.assignment2.R.styleable.View;

/**
 * Created by morgana4 on 3/4/17.
 */

public class LightGraph extends View {

    double[] _meanList;
    double[] _valueList;
    double[] _sdList;

    public LightGraph(Context context) {
        super(context);

        _meanList = new double[3];
        _valueList = new double[3];
        _sdList = new double[3];

    }

    public LightGraph(Context context, AttributeSet attrs) {
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

        //compensate for fucked up pixel scale
        float valueA = 600-(((float)_valueList[0]* 600/50));
        float valueB = 600-(((float)_valueList[1]* 600/50));
        float valueC = 600-(((float)_valueList[2]* 600/50));

        float meanA = 600-((float)_meanList[0]* 600/50);
        float meanB = 600-((float)_meanList[1]* 600/50);
        float meanC = 600-((float)_meanList[2]* 600/50);

        float sdA = 600-((float)_sdList[0]* 600/50);
        float sdB = 600-((float)_sdList[1]* 600/50);
        float sdC = 600-((float)_sdList[2]* 600/50);

        //graph shit
        canvas.drawLine(0,0,600,0,grey);
        canvas.drawLine(0,35,600,35,grey);
        canvas.drawLine(0,70,600,70,grey);
        canvas.drawLine(0,105,600,105,grey);
        canvas.drawLine(0,140,600,140,grey);
        canvas.drawLine(0,175,600,175,grey);
        canvas.drawLine(0,210,600,210,grey);
        canvas.drawLine(0,245,600,245,grey);
        canvas.drawLine(0,270,600,270,grey);
        canvas.drawLine(0,305,600,305,grey);
        canvas.drawLine(0,340,600,340,grey);
        canvas.drawLine(0,375,600,375,grey);
        canvas.drawLine(0,410,600,410,grey);
        canvas.drawLine(0,445,600,445,grey);
        canvas.drawLine(0,480,600,480,grey);
        canvas.drawLine(0,515,600,515,grey);
        canvas.drawLine(0,550,600,550,grey);
        canvas.drawLine(0,585,600,585,grey);

        canvas.drawLine(0,0,0,600,grey);
        canvas.drawLine(300,0,300,600,grey);
        canvas.drawLine(600,0,600,600,grey);

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
