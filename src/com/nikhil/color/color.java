package com.nikhil.color;

import com.color.R;
import com.color.R.id;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class color extends Activity 
{
	public SeekBar trkred;
	public SeekBar trkgreen;
	public SeekBar trkblue;
	public TextView txtvalueupper;
	public TextView txtvaluelower;
	
	public TextView txtupper;
	public TextView txtlower;
	public String click = "upper";
	
	public Vibrator vibrator;
	
	int red_upper = 0, red_lower = 0, green_upper = 0, green_lower = 0, blue_upper = 0, blue_lower = 0;
	
    public void onCreate(Bundle savedInstanceState) 
    {	 	
    	WindowManager.LayoutParams screen = getWindow().getAttributes();
        screen.screenBrightness = (float) 0.5;
        getWindow().setAttributes(screen);
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.color);    	
    	
        trkred = (SeekBar) findViewById(id.SeekBar01);
        trkgreen = (SeekBar) findViewById(id.SeekBar02);
        trkblue = (SeekBar) findViewById(id.SeekBar03);
        
        txtvalueupper = (TextView) findViewById(id.TextView01);
        txtvaluelower = (TextView) findViewById(id.TextView04);
        
        txtupper = (TextView) findViewById(id.TextView02);
        txtlower = (TextView) findViewById(id.TextView03);
        
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        
        trkred.setIndeterminate(false);
        trkgreen.setIndeterminate(false);
        trkblue.setIndeterminate(false);
        
        //TO IMPLEMENT EVENT ONSCROLL FOR TRKRED
        trkred.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{
				seek_color();
			}
			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        //TO IMPLEMENT EVENT ONSCROLL FOR TRKGREEN
        trkgreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{	
				seek_color();
			}
			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
		});
        
        //TO IMPLEMENT EVENT ONSCROLL FOR TRKBLUR
        trkblue.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{	
				seek_color();
			}				
			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
		});
    }
    
    public void seek_color() 
    {
    	if(click.toString() == "upper")
    	{
    		red_upper = trkred.getProgress();
    		green_upper = trkgreen.getProgress();
    		blue_upper = trkblue.getProgress();
    		
    		txtupper.setBackgroundColor(Color.rgb(red_upper, green_upper, blue_upper));
    		txtvalueupper.setText("Upper: R:" + red_upper + " G:" + green_upper + " B:" + blue_upper);
    	}
    	else
    	{
    		red_lower = trkred.getProgress();
    		green_lower = trkgreen.getProgress();
    		blue_lower = trkblue.getProgress();
    		
    		txtlower.setBackgroundColor(Color.rgb(red_lower, green_lower, blue_lower));
    		txtvaluelower.setText("Lower: R:" + red_lower + " G:" + green_lower + " B:" + blue_lower);
    	}
	}
    
    public void upperclick(View v)
    {
    	txtupper.setText("SELECTED");
    	txtlower.setText("");
    	click = "upper";
    	vibrator.vibrate(30);
    	
    	new Thread()
    	{
    		public void run()
    		{
    			trkred.setProgress(red_upper);
    		}
    	}.start();
    	
    	new Thread()
    	{
    		public void run() 
    		{
    			trkgreen.setProgress(green_upper);
			}
    	}.start();
    	
    	new Thread()
    	{
    		public void run() 
    		{
    			trkblue.setProgress(blue_upper);
			}
    	}.start();
    }

    public void lowerclick(View v)
    {
    	txtlower.setText("SELECTED");
    	txtupper.setText("");
    	click = "lower";
    	vibrator.vibrate(30);
    	
    	new Thread()
    	{
    		public void run()
    		{
    			trkred.setProgress(red_lower);	
    		}
    	}.start();
    	
    	new Thread()
    	{
    		public void run()
    		{
    			trkgreen.setProgress(green_lower);
			}
    	}.start();
    	
    	new Thread()
    	{
    		public void run()
    		{
    			trkblue.setProgress(blue_lower);
			}
    	}.start();
    	
    }
}
