package com.apradanas.simplestyledialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

public class SimpleStyleDialog extends Dialog {
	// ui
	private Button mLeftButton;
	private Button mMiddleButton;
	private Button mRightButton;
	private TextView mDialogTitleTextView;
	private TextView mDialogMessageTextView;
	private View mDialogView;
	private View mLeftSeparator;
	private View mRightSeparator;
	
	// listener
	private OnSimpleClickListener mLeftButtonListener;
	private OnSimpleClickListener mMiddleButtonListener;
	private OnSimpleClickListener mRightButtonListener;
	
	// animation
	private AnimationSet mInAnim;
    private AnimationSet mOutAnim;
	
	// var
	private int mButtonCount;
	private int mDialogType;
	private String mDialogTitleText;
	private String mDialogMessageText;
	private String mRightButtonText;
	private String mMiddleButtonText;
	private String mLeftButtonText;
	
	// dialog type
	public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;
	public static final int DIALOG_TYPE_DOUBLE_BUTTON = 2;
	public static final int DIALOG_TYPE_TRIPLE_BUTTON = 3;
	
	public static interface OnSimpleClickListener {
		public void onClick(SimpleStyleDialog simpleStyleDialog);
	}

	public SimpleStyleDialog(Context context) {
		this(context, DIALOG_TYPE_SINGLE_BUTTON);
	}
	
	private SimpleStyleDialog(Context context, int dialogType) {
		super(context, R.style.simple_dialog_style);
		
		mButtonCount = dialogType;
		
		setCancelable(true);
		setDialogType(dialogType);
		
		mInAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mOutAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        mOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        SimpleStyleDialog.super.dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
	}
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
    	mLeftButton = (Button) findViewById(R.id.dialog_button_leftButtonControl);
    	mMiddleButton = (Button) findViewById(R.id.dialog_button_middleButtonControl);
    	mRightButton = (Button) findViewById(R.id.dialog_button_rightButtonControl);
    	mDialogTitleTextView = (TextView) findViewById(R.id.dialog_textView_title);
    	mDialogMessageTextView = (TextView) findViewById(R.id.dialog_textView_message);
    	mLeftSeparator = (View) findViewById(R.id.dialog_view_leftSeparator);
    	mRightSeparator = (View) findViewById(R.id.dialog_view_rightSeparator);
    	
    	mLeftButton.setOnClickListener(dialogClickListener);
    	mMiddleButton.setOnClickListener(dialogClickListener);
    	mRightButton.setOnClickListener(dialogClickListener);
    	
    	// set dialog type based on number of button
    	setDialogType(mButtonCount);
    	
    	setTitle(getTitle());
    	setMessage(getMessage());
    	setRightButtonText(getRightButtonText());
    	setMiddleButtonText(getMiddleButtonText());
    	setLeftButtonText(getLeftButtonText());
    	changeDialogType(getDialogType(), true);
	}
	
	protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
    }
	
	public android.view.View.OnClickListener dialogClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.dialog_button_leftButtonControl) {
				if(mLeftButtonListener != null) {
					mLeftButtonListener.onClick(SimpleStyleDialog.this);
				} else {
					dismiss();
				}
			} else if (id == R.id.dialog_button_middleButtonControl) {
				if(mMiddleButtonListener != null) {
					mMiddleButtonListener.onClick(SimpleStyleDialog.this);
				} else {
					dismiss();
				}
			} else if (id == R.id.dialog_button_rightButtonControl) {
				if(mRightButtonListener != null) {
					mRightButtonListener.onClick(SimpleStyleDialog.this);
				} else {
					dismiss();
				}
			}
		}
	};
	
	public SimpleStyleDialog setLeftButton(String text, OnSimpleClickListener listener) {
		mButtonCount++;
		mLeftButtonListener = listener;
		setLeftButtonText(text);
		return this;
	}
	
	public SimpleStyleDialog setMiddleButton(String text, OnSimpleClickListener listener) {
		mButtonCount++;
		mMiddleButtonListener = listener;
		setMiddleButtonText(text);
		return this;
	}
	
	public SimpleStyleDialog setRightButton(String text, OnSimpleClickListener listener) {
		mRightButtonListener = listener;
		setRightButtonText(text);
		return this;
	}
	
	private void resetView() {
		mMiddleButton.setVisibility(View.GONE);
		mLeftButton.setVisibility(View.GONE);
		mRightSeparator.setVisibility(View.GONE);
		mLeftSeparator.setVisibility(View.GONE);
	}
	
	public SimpleStyleDialog changeDialogType(int dialogType) {
		changeDialogType(dialogType, false);
		return this;
	}
	
	private void changeDialogType(int dialogType, boolean isFromCreate) {
		setDialogType(dialogType);
		
		if(mDialogView != null) {
			if(!isFromCreate) {
				resetView();
			}
			
			switch(dialogType) {
			case DIALOG_TYPE_SINGLE_BUTTON:
				mRightButton.setBackgroundResource(R.drawable.single_button_background);
				break;
			case DIALOG_TYPE_DOUBLE_BUTTON:
				mRightButton.setBackgroundResource(R.drawable.right_button_background);
				mRightSeparator.setVisibility(View.VISIBLE);
				mLeftButton.setVisibility(View.VISIBLE);
				break;
			case DIALOG_TYPE_TRIPLE_BUTTON:
				mRightButton.setBackgroundResource(R.drawable.right_button_background);
				mRightSeparator.setVisibility(View.VISIBLE);
				mMiddleButton.setVisibility(View.VISIBLE);
				mLeftSeparator.setVisibility(View.VISIBLE);
				mLeftButton.setVisibility(View.VISIBLE);
				break;
			}
		}
	}
	
	private void setDialogType(int dialogType) {
		mDialogType = dialogType;
	}
	
	private int getDialogType() {
		return mDialogType;
	}
	
	public SimpleStyleDialog setTitle(String title) {
		mDialogTitleText = title;
		if(mDialogTitleTextView != null && mDialogTitleText != null) {
			if(mDialogTitleText.equals("")) {
				mDialogTitleTextView.setVisibility(View.GONE);
			} else {
				mDialogTitleTextView.setVisibility(View.VISIBLE);
				mDialogTitleTextView.setText(mDialogTitleText);
			}
		}
		return this;
	}
	
	private String getTitle() {
		if(mDialogTitleText != null) {
			return mDialogTitleText;
		}
		return "";
	}
	
	public SimpleStyleDialog setMessage(String message) {
		mDialogMessageText = message;
		if(mDialogMessageTextView != null && mDialogMessageText != null) {
			if(mDialogMessageText.equals("")) {
				mDialogMessageTextView.setVisibility(View.GONE);
			} else {
				mDialogMessageTextView.setVisibility(View.VISIBLE);
				mDialogMessageTextView.setText(mDialogMessageText);
			}
		}
		return this;
	}
	
	private String getMessage() {
		if(mDialogMessageText != null) {
			return mDialogMessageText;
		}
		return "";
	}
	
	private void setRightButtonText(String text) {
		mRightButtonText = text;
		if(mRightButton != null && mRightButtonText != null) {
			mRightButton.setText(mRightButtonText);
		}
	}
	
	private String getRightButtonText() {
		if(mRightButtonText != null) {
			return mRightButtonText;
		}
		return "";
	}
	
	private void setMiddleButtonText(String text) {
		mMiddleButtonText= text;
		if(mMiddleButton != null && mMiddleButtonText != null) {
			mMiddleButton.setText(mMiddleButtonText);
		}
	}
	
	private String getMiddleButtonText() {
		if(mMiddleButtonText != null) {
			return mMiddleButtonText;
		}
		return "";
	}

	private void setLeftButtonText(String text) {
		mLeftButtonText = text;
		if(mLeftButton != null && mLeftButtonText != null) {
			mLeftButton.setText(mLeftButtonText);
		}
	}
	
	private String getLeftButtonText() {
		if(mLeftButtonText != null) {
			return mLeftButtonText;
		}
		return "";
	}
}
