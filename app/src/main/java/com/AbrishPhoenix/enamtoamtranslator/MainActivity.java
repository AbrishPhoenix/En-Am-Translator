package com.AbrishPhoenix.enamtoamtranslator;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.speech.tts.TextToSpeech;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private FloatingActionButton _fab;
	private double mode = 0;
	
	private LinearLayout linear7;
	private LinearLayout linear4;
	private LinearLayout linear1;
	private LinearLayout linear6;
	private TextView textview3;
	private LinearLayout linear5;
	private ScrollView vscroll1;
	private LinearLayout linear18;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear9;
	private LinearLayout linear16;
	private EditText edittext1;
	private LinearLayout linear15;
	private ImageView imageview7;
	private TextView textview11;
	private TextView textview9;
	private ImageView imageview2;
	private TextView textview8;
	private ImageView imageview3;
	private TextView textview4;
	private LinearLayout linear13;
	private LinearLayout linear17;
	private TextView textview2;
	private LinearLayout linear14;
	private ImageView imageview6;
	private TextView textview10;
	private ImageView imageview4;
	private TextView textview6;
	
	private TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear7 = findViewById(R.id.linear7);
		linear4 = findViewById(R.id.linear4);
		linear1 = findViewById(R.id.linear1);
		linear6 = findViewById(R.id.linear6);
		textview3 = findViewById(R.id.textview3);
		linear5 = findViewById(R.id.linear5);
		vscroll1 = findViewById(R.id.vscroll1);
		linear18 = findViewById(R.id.linear18);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear9 = findViewById(R.id.linear9);
		linear16 = findViewById(R.id.linear16);
		edittext1 = findViewById(R.id.edittext1);
		linear15 = findViewById(R.id.linear15);
		imageview7 = findViewById(R.id.imageview7);
		textview11 = findViewById(R.id.textview11);
		textview9 = findViewById(R.id.textview9);
		imageview2 = findViewById(R.id.imageview2);
		textview8 = findViewById(R.id.textview8);
		imageview3 = findViewById(R.id.imageview3);
		textview4 = findViewById(R.id.textview4);
		linear13 = findViewById(R.id.linear13);
		linear17 = findViewById(R.id.linear17);
		textview2 = findViewById(R.id.textview2);
		linear14 = findViewById(R.id.linear14);
		imageview6 = findViewById(R.id.imageview6);
		textview10 = findViewById(R.id.textview10);
		imageview4 = findViewById(R.id.imageview4);
		textview6 = findViewById(R.id.textview6);
		tts = new TextToSpeech(getApplicationContext(), null);
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Translator.Utils.showKeyboard(true, edittext1);
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textview2.setText(Translator.getTranslated(MainActivity.this, edittext1.getText().toString()));
				if (_charSeq.equals("")) {
					ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(linear3, "alpha", 1.0f, 0f);
					alphaAnimator.setDuration(300);
					alphaAnimator.addListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
									super.onAnimationEnd(animation);
									linear3.setVisibility(View.GONE);
							}
							
							@Override
							public void onAnimationStart(Animator animation) {
									super.onAnimationStart(animation);
									
							}
					});
					alphaAnimator.start();
					
				}
				else {
					if (linear3.getVisibility() == View.GONE) {
						linear3.setAlpha(0f);
						linear3.setVisibility(View.VISIBLE);
						ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(linear3, "alpha", 0f, 1.0f);
						alphaAnimator.setDuration(300);
						alphaAnimator.start();
						
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				
				if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClip().getItemCount() > 0) {
						CharSequence text = clipboard.getPrimaryClip().getItemAt(0).getText();
						
						
						edittext1.setText(text);
				}
				
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext1.getText().toString().equals("")) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", edittext1.getText().toString()));
				}
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				tts.speak(textview2.getText().toString(), TextToSpeech.QUEUE_ADD, null);
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!textview2.getText().toString().equals("")) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview2.getText().toString()));
				}
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if(mode == 0) {
					    Translator.sync();
					    textview4.setText("Amharic");
					    textview6.setText("English");
					    mode = 1;
				} else {
					    Translator.sync();
					    textview4.setText("English");
					    textview6.setText("Amharic");
					    mode = 0;
				}
				edittext1.setText("");
			}
		});
	}
	
	private void initializeLogic() {
		
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)3, 0xFF00E676, 0xFFE8F5E9));
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)3, 0xFF00E676, 0xFF00E676));
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
