package com.villevalta.thingspeakclient.ui.adapters;

import com.villevalta.thingspeakclient.model.Pagination;
import com.villevalta.thingspeakclient.model.ListViewable;
import com.villevalta.thingspeakclient.ui.adapters.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by villevalta on 25.3.2015.
 */
public class ListContentProvider extends ArrayList<ListViewable> {

	private RecyclerListAdapter mRecyclerListAdapter = null;
	private boolean refreshable;
	private Pagination mPagination;

	public void setAdapter(RecyclerListAdapter adapter){
		this.mRecyclerListAdapter = adapter;
	}

	@Override
	public boolean add(final ListViewable object){
		super.add(object);
		if(mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemInserted(size() - 1);
		return true; // return always true
	}

	@Override
	public void add(int index, final ListViewable object){
		super.add(index,object);
		if(mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemInserted(index);
	}

	@Override
	public boolean addAll(final Collection<? extends ListViewable> collection) {
		boolean result = super.addAll(collection);
		if(result && mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemRangeInserted(size() - collection.size(), collection.size());
		return result;
	}

	@Override
	public boolean addAll(int index, final Collection<? extends ListViewable> collection) {
		boolean result = super.addAll(collection);
		if(result && mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemRangeInserted(index, collection.size());
		return result;
	}

	@Override public ListViewable set(int index, ListViewable object) {
		ListViewable result = super.set(index, object);
		mRecyclerListAdapter.notifyItemChanged(index);
		return result;
	}

	@Override public ListViewable remove(int index) {
		ListViewable object = super.remove(index);
		if(mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemRemoved(index);
		return object;
	}

	@Override
	public boolean remove(Object object){
		int position = indexOf(object);
		boolean result = super.remove(object);
		if(result && mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemRemoved(position);

		return result;
	}

	@Override protected void removeRange(int fromIndex, int toIndex) {
		super.removeRange(fromIndex, toIndex);
		if(mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemRangeRemoved(fromIndex, toIndex);
	}

	@Override
	public void clear(){
		int size = size();
		super.clear();
		if(mRecyclerListAdapter != null) mRecyclerListAdapter.notifyItemRangeRemoved(0,size);
	}


	public boolean isRefreshable() {
		return refreshable;
	}

	public Pagination getPagination() {
		return mPagination;
	}

	public void setPagination(Pagination pagination) {
		mPagination = pagination;
	}
}
