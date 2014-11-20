package com.apradanas.simplestyledialogsample;

import com.apradanas.simplestyledialog.SimpleStyleDialog;
import com.apradanas.simplestyledialog.SimpleStyleDialog.OnSimpleClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.sample_button_basicMessage).setOnClickListener(globalClickListener);
        findViewById(R.id.sample_button_basicMessageBold).setOnClickListener(globalClickListener);
        findViewById(R.id.sample_button_singleButtonDialog).setOnClickListener(globalClickListener);
        findViewById(R.id.sample_button_doubleButtonDialog).setOnClickListener(globalClickListener);
        findViewById(R.id.sample_button_tripleButtonDialog).setOnClickListener(globalClickListener);
    }
    
    protected OnClickListener globalClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.sample_button_basicMessage:
					showBasicMessage();
					break;
				case R.id.sample_button_basicMessageBold:
					showBasicMessageBold();
					break;
				case R.id.sample_button_singleButtonDialog:
					showSingleButtonDialog();
					break;
				case R.id.sample_button_doubleButtonDialog:
					showDoubleButtonDialog();
					break;
				case R.id.sample_button_tripleButtonDialog:
					showTripleButtonDialog();
					break;
			}
		}
	};
	
	private void showBasicMessage() {
		new SimpleStyleDialog(this)
		.setMessage("A basic message")
		.setRightButton("Okay", null)
		.show();
	}
	
	private void showBasicMessageBold() {
		new SimpleStyleDialog(this)
		.setTitle("A bold message")
		.setRightButton("Okay", null)
		.show();
	}
	
	private void showSingleButtonDialog() {
		new SimpleStyleDialog(this)
		.setTitle("A Title")
		.setMessage("A Message")
		.setRightButton("Ok", null)
		.show();
	}
	
	private void showDoubleButtonDialog() {
		new SimpleStyleDialog(this)
		.setTitle("Hello!")
		.setMessage("Do you want to see another dialog?")
		.setRightButton("Sure!", new OnSimpleClickListener() {
			
			@Override
			public void onClick(SimpleStyleDialog simpleStyleDialog) {
				simpleStyleDialog.dismiss();
				new SimpleStyleDialog(MainActivity.this)
				.setTitle("Another Title")
				.setMessage("Another Message")
				.setRightButton("Cool!", null)
				.show();
			}
		})
		.setLeftButton("No!", null)
		.show();
	}
	
	private void showTripleButtonDialog() {
		new SimpleStyleDialog(this)
		.setTitle("Warning")
		.setMessage("Do you want to save your imaginary changes?")
		.setRightButton("Yes", new OnSimpleClickListener() {
			
			@Override
			public void onClick(SimpleStyleDialog simpleStyleDialog) {
				simpleStyleDialog.setTitle("Success!")
								.setMessage("Your imaginary changes have been saved")
								.setRightButton("Ok", null)
								.changeDialogType(SimpleStyleDialog.DIALOG_TYPE_SINGLE_BUTTON);
				
			}
		})
		.setLeftButton("No", new OnSimpleClickListener() {
			
			@Override
			public void onClick(SimpleStyleDialog simpleStyleDialog) {
				simpleStyleDialog.setTitle("")
								.setMessage("Are you sure about this?")
								.setRightButton("Yes", null)
								.setLeftButton("No", new OnSimpleClickListener() {
									
									@Override
									public void onClick(SimpleStyleDialog simpleStyleDialog) {
										simpleStyleDialog.setTitle("")
														.setMessage("Good decision. Your imaginary changes have been saved")
														.setRightButton("Ok", null)
														.changeDialogType(SimpleStyleDialog.DIALOG_TYPE_SINGLE_BUTTON);
									}
								})
								.changeDialogType(SimpleStyleDialog.DIALOG_TYPE_DOUBLE_BUTTON);
			}
		})
		.setMiddleButton("Cancel", null)
		.show();
	}
}
