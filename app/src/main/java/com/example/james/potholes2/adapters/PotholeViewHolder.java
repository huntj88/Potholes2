package com.example.james.potholes2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.james.potholes2.R;
import com.example.james.potholes2.retrofit.model.pothole.Pothole;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 4/17/2017.
 */

public class PotholeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.pothole_text)
    TextView potholeText;
    Pothole pothole;

    public PotholeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setPothole(Pothole pothole)
    {
        this.pothole = pothole;
        String text = "Pothole: "+pothole.getPotholeID();
        potholeText.setText(text);
    }
}
