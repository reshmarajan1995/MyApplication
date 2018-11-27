package com.example.pop.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Swathy on 27-11-2018.
 */

public class AdapterFansView extends RecyclerView.Adapter<AdapterFansView.MyViewHolder> {
    List<BeanViewFans> beanlist=null;
    Context c=null;


    public AdapterFansView(List<BeanViewFans> beanlist, ViewElephantBookingsByFans viewDoctorDetails) {
        this.beanlist=beanlist;
        c=viewDoctorDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_fans_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BeanViewFans b = beanlist.get(position);
        holder.elname.setText(b.getEname());
        holder.need.setText(b.getNeed());

        holder.date.setText(b.getDate());




    }

    @Override
    public int getItemCount() {
        return beanlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView elname;
        TextView date;
        TextView need;

        public MyViewHolder(View itemView) {
            super(itemView);
            elname= (TextView)itemView.findViewById(R.id.eName);
            need= (TextView)itemView.findViewById(R.id.eNeed);
            date= (TextView)itemView.findViewById(R.id.eDate);

        }
    }


}
