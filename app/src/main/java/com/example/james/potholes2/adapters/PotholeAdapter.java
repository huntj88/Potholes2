package com.example.james.potholes2.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.potholes2.R;
import com.example.james.potholes2.retrofit.model.pothole.Pothole;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by James on 4/17/2017.
 */

public class PotholeAdapter extends RealmRecyclerViewAdapter<Pothole,PotholeViewHolder> {


    public PotholeAdapter(@Nullable OrderedRealmCollection<Pothole> data, boolean autoUpdate) {
        super(data, autoUpdate);
        //setHasStableIds(true);
    }

    @Override
    public PotholeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.view_holder_pothole, parent, false);
        return new PotholeViewHolder(root);
    }

    @Override
    public void onBindViewHolder(PotholeViewHolder holder, int position) {
        final Pothole obj = getItem(position);
        holder.setPothole(obj);
    }

    @Override
    public long getItemId(int index) {
        //noinspection ConstantConditions
        return getItem(index).getPotholeID();
    }


}
