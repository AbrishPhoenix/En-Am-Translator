package com.AbrishPhoenix.enamtoamtranslator;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.*;
import android.app.Activity;
import android.widget.EditText;

public class Translator {
	
	public static HashMap<String, Object> wordsMap = new HashMap<>();
	public static ArrayList<String> wordsKeyArray = new ArrayList<>();
	public static ArrayList<String> wordsArray = new ArrayList<>();
	
	public static String input = "";
	public static Context context;
	
	public static String getTranslated(final Context _context, final String _input) {
		context = _context;
		if(wordsMap.size() == 0) {
			init();
		}
		input = _input.toLowerCase();
		ArrayList<String> toTrans = split(input);
		String returnResult = "";
		for(int i = 0; i < toTrans.size(); i++) {
			String str = toTrans.get(i);
			if(isKey(str) || isWord(str) || isSymbol(str)) {
				if(isKey(str)) {
					str = wordsMap.get(str).toString();
				}
				returnResult = returnResult + str;
			} else {
				
			}
		}
		return returnResult;
	}
	
	public static void init() {
		wordsMap = Utils.getHashMap(Utils.readFromAssets(context, "words.json"));
		init2();
	}
	
	public static void init2() {
		wordsKeyArray = Utils.getKeys(wordsMap);
		wordsArray = Utils.getValues(wordsMap);
	}
	
	public static void sync() {
		HashMap<String, Object> hm = new HashMap<>();
		Utils.getKeyValueShifted(wordsMap, hm);
		wordsMap = hm;
		init2();
	}
	
	public static boolean isKey(String cr) {
		return Utils.contains(wordsKeyArray, cr);
	}
	public static boolean isWord(String cr) {
		return Utils.contains(wordsArray, cr);
	}
	public static boolean isSymbol(String cr) {
		return !isKey(cr) && !isWord(cr);
	}
	
	public static ArrayList<String> split(String _st) {
		ArrayList<String> sp = new ArrayList<>();
		String strSource = _st;
		while(strSource.length() > 0) {
			try{
				int flag = 0;
				String str = "";
				for(int i = 6; i > 0; i--) {
					try{
						str = strSource.substring(0, i);
					}catch(Exception e) {}
					
					if(isKey(str)) {
						sp.add(str);
						if(strSource.length() > i) {
							strSource = strSource.substring(i);
						} else {
							strSource = "";
						}
						flag = 1;
						break;
					}
				}
				if(flag == 0) {
					str = strSource.substring(0, 1);
					if(isWord(str) || isSymbol(str)) {
						sp.add(str);
						if(strSource.length() > 1) {
							strSource = strSource.substring(1);
						} else {
							strSource = "";
						}
					}
				}
			}catch(Exception e){
				break;
			}
		}
		return sp;
	}
	
	
	
	public static class Utils {
		public static boolean contains(ArrayList list, String cr) {
			if(list != null && list.size() > 0 && list.contains(cr)) return true;
			return false;
		}
		
		public static ArrayList<String> split(String _st, String _sp) {
			String[] tokens = {};
			try{
				tokens = _st.split(_sp);
			} catch (Exception e){}
			ArrayList<String> st = new ArrayList<>();
			for(String str : tokens) {
				st.add(str);
			}
			return st;
		}
		
		public static ArrayList<String> getKeys(HashMap<String, Object> hm) {
			ArrayList<String> sg = new ArrayList<>();
			getAllKeyFromMap(hm, sg);
			return sg;
		}
		public static ArrayList<String> getValues(HashMap<String, Object> hm) {
			ArrayList<String> sg = new ArrayList<>();
			getAllValueFromMap(hm, sg);
			return sg;
		}
		
		public static void getAllValueFromMap(HashMap<String, Object> _map, ArrayList<String> _output) {
			if (_output == null) return;
			_output.clear();
			if (_map == null || _map.size() < 1) return;
			for (Map.Entry<String, Object> _entry : _map.entrySet()) {
				_output.add(_entry.getValue().toString());
			}
		}
		
		public static void getAllKeyFromMap(HashMap<String, Object> _map, ArrayList<String> _output) {
			if (_output == null) return;
			_output.clear();
			if (_map == null || _map.size() < 1) return;
			for (Map.Entry<String, Object> _entry : _map.entrySet()) {
				_output.add(_entry.getKey());
			}
		}
		
		public static void getKeyValueShifted(HashMap<String, Object> _map, HashMap<String, Object> _output) {
			if (_output == null) return;
			_output.clear();
			if (_map == null || _map.size() < 1) return;
			for (Map.Entry<String, Object> _entry : _map.entrySet()) {
				_output.put(_entry.getValue().toString(), _entry.getKey());
			}
		}
		
		public static HashMap<String, Object> getHashMap(String st) {
			if(st.equals("")) return new HashMap<>();
			return ((HashMap<String, Object>) new Gson().fromJson(st, new TypeToken<HashMap<String, Object>>(){}.getType()));
		}
		
		public static String readFromAssets(Context _context, String name) {
			String returnn = "";
			try{
				java.io.InputStream StfnfIn = ((Activity) _context).getAssets().open(name);
				int StfnfSi = StfnfIn.available();
				byte[] StfnfBu = new byte[StfnfSi];
				StfnfIn.read(StfnfBu);
				StfnfIn.close();
				returnn = new String(StfnfBu, "UTF-8");
			}catch(Exception e){
				
			}
			return returnn;
		}
		
		public static void showKeyboard(boolean truee, EditText txt) {
			if(truee) {
				if(txt.requestFocus()){
					android.view.inputmethod.InputMethodManager imm =(android.view.inputmethod.InputMethodManager) txt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(txt, android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT);
				}
			} else {
				((android.view.inputmethod.InputMethodManager) txt.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(txt.getWindowToken(), 0);
				txt.clearFocus();
			}
		}
	}
}