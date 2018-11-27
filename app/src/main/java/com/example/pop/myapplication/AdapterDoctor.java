package com.example.pop.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Swathy on 25-11-2018.
 */

public class AdapterDoctor extends RecyclerView.Adapter<AdapterDoctor.MyViewHolder> {
        List<BeanDoctor> beanlist=null;
        Context c=null;


public AdapterDoctor(List<BeanDoctor> beanlist, ViewDoctorDetails viewDoctorDetails) {
        this.beanlist=beanlist;
        c=viewDoctorDetails;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_elephant,parent,false);
        return new MyViewHolder(v);
        }

@Override
public void onBindViewHolder(MyViewHolder holder, int position) {
    BeanDoctor b = beanlist.get(position);
        holder.elname.setText(b.getName());
        holder.elheight.setText(b.getContact());
        // holder.elrate.setText(b.getElephantname());
        // holder.elownname.setText(b.getElephantheight());
        holder.elrate.setText(b.getPlace());
        holder.elownname.setText(b.getQualification());
      //  Log.d("_elephantphoto",b.getElephantphoto());

String url="http://192.168.43.233:8084/myelephant/Doctor/Image/"+b.getPhoto();
Log.d("_accessoriesphoto",url);
        Picasso.with(c).load(url).into(holder.elephoto);


        }

@Override
public int getItemCount() {
        return beanlist.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView elname;
    TextView elrate;
    TextView elheight;
    TextView elownname;
    ImageView elephoto;
    public MyViewHolder(View itemView) {
        super(itemView);
        elname= (TextView)itemView.findViewById(R.id.elepname);
        elrate= (TextView)itemView.findViewById(R.id.eleprate);
        elheight= (TextView)itemView.findViewById(R.id.elepheight);
        elownname= (TextView)itemView.findViewById(R.id.elepownname);
        elephoto= (ImageView) itemView.findViewById(R.id.elepphoto);
    }
}


}
