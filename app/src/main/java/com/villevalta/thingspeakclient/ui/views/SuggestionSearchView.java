package com.villevalta.thingspeakclient.ui.views;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Created by villevalta on 25.4.2015.
 */
public class SuggestionSearchView extends SearchView implements SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

	SuggestionsDatabase mDatabase;
	SuggestionSearchViewCallbacks listener;
	int minQueryLength = 1;

	public SuggestionSearchView(Context context) {
		super(context);
		init();
	}

	public SuggestionSearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SuggestionSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init(){
		super.setOnQueryTextListener(this);
		super.setOnSuggestionListener(this);
	}

	public void setSuggestionTable(String suggestionTable){
		mDatabase = new SuggestionsDatabase(getContext(), suggestionTable);
	}

	public void setMinQueryLength(int length){
		if(length <= 0) throw new IllegalArgumentException("Length must be more than zero.");
		this.minQueryLength = length;
	}

	public void setCallbacksListener(SuggestionSearchViewCallbacks listener){
		this.listener = listener;
	}

	@Override
	public boolean onQueryTextSubmit(String s) {

		if(s.length() >= minQueryLength){
			if(mDatabase != null) mDatabase.insertSuggestion(s);
			if(listener != null) listener.onSubmit(s);
			clearFocus();
		}else{
			Toast.makeText(getContext(),"Min. "+minQueryLength+" characters.",Toast.LENGTH_SHORT).show();
		}

		//true if the query has been handled by the listener, false to let the SearchView perform the default action.
		return true;
	}

	@Override
	public boolean onQueryTextChange(String s) {
		//false if the SearchView should perform the default action of showing any suggestions if available, true if the action was handled by the listener.
		if(s.length() == 0 && listener != null){
			listener.onCleared();
		}


		// todo: show suggestions
		 if(mDatabase != null){
			Cursor cursor = mDatabase.getSuggestions(s);
			 if(cursor.getCount() > 0){
				String[] columns = new String[]{SuggestionsDatabase.FIELD_SUGGESTION};
				int[] columnTextId = new int[] { android.R.id.text1};
				setSuggestionsAdapter(new SearchSuggestionSimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, cursor, columns, columnTextId, 0));
			 }
		}



		return false;
	}

	@Override
	public boolean onSuggestionSelect(int position) {
		return false;
	}

	@Override
	public boolean onSuggestionClick(int position) {
		SQLiteCursor cursor = (SQLiteCursor) getSuggestionsAdapter().getItem(position);
		int indexColumnSuggestion = cursor.getColumnIndex(SuggestionsDatabase.FIELD_SUGGESTION);
		setQuery(cursor.getString(indexColumnSuggestion), false);
		return true;
	}

	@Override
	public void setOnQueryTextListener(OnQueryTextListener listener) {
		// The OnQueryTextListener can only be implemented by SuggestionSearchView.
		throw new UnsupportedOperationException("The OnQueryTextListener can only be implemented by SuggestionSearchView. Please use setCallbacksListener()");
	}

	public interface SuggestionSearchViewCallbacks{
		void onSubmit(String query);
		void onCleared();
	}

	class SuggestionsDatabase {

		public static final String DB_SUGGESTION = "DB_SEARCH_SUGGESTION";
		private  String suggestionTable = null;
		public final static String FIELD_ID = "_id";
		public final static String FIELD_SUGGESTION = "suggestion";

		private SQLiteDatabase db;
		private Helper helper;

		public SuggestionsDatabase(Context context, String table) {
			suggestionTable = table;
			helper = new Helper(context, DB_SUGGESTION, null, 1);
			db = helper.getWritableDatabase();
		}

		public long insertSuggestion(String text)
		{
			ContentValues values = new ContentValues();
			values.put(FIELD_SUGGESTION, text);
			return db.insert(suggestionTable, null, values);
		}

		public Cursor getSuggestions(String text) //UNIQUE ON CONFLICT REPLACE
		{
			// TODO make nicer ?
			text +="%";
			return db.query(suggestionTable, new String[]{FIELD_ID, FIELD_SUGGESTION}, FIELD_SUGGESTION + " LIKE " + DatabaseUtils.sqlEscapeString(text), null, null, null, null);
		}


		private class Helper extends SQLiteOpenHelper
		{
			public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
				super(context, name, factory, version);
			}
			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL("CREATE TABLE " + suggestionTable + " (" + FIELD_ID + " integer primary key autoincrement, " + FIELD_SUGGESTION + " TEXT UNIQUE ON CONFLICT REPLACE);");
			}
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
		}
	}

	public class SearchSuggestionSimpleCursorAdapter extends SimpleCursorAdapter
	{
		public SearchSuggestionSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
			super(context, layout, c, from, to, flags);
		}
		@Override
		public CharSequence convertToString(Cursor cursor) {
			int indexColumnSuggestion = cursor.getColumnIndex(SuggestionsDatabase.FIELD_SUGGESTION);
			return cursor.getString(indexColumnSuggestion);
		}
	}
}
