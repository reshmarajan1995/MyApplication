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

public class AdapterAcce extends RecyclerView.Adapter <AdapterAcce.MyViewHolder> {
    List<Beanaccessories> beanlist=null;
    Context c=null;



    public AdapterAcce(List<Beanaccessories> beanlist, ViewAccDetails viewAccDetails) {
        this.beanlist=beanlist;
        c = viewAccDetails;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_accessories,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Beanaccessories b = beanlist.get(position);
        holder.accename.setText(b.getAccessoriesname());
       holder.accerate.setText(b.getAccessoriesrate());
       holder.accecount.setText(b.getAccessoriescount());
        holder.acceownname.setText(b.getAccessoriesownername());
        Log.d("_accessoriesphoto",b.getAccessoriesphotophoto());

             Picasso.with(c)
               .load("http://192.168.43.233:8084/myelephant/acceowner/image/"+b.getAccessoriesphotophoto())
              .into(holder.accephoto);


    }

    @Override
    public int getItemCount() {
        return beanlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView accename;
        TextView accerate;
        TextView accecount;
        TextView acceownname;
        ImageView accephoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            accename= (TextView)itemView.findViewById(R.id.accename);
            accerate= (TextView)itemView.findViewById(R.id.accesrate);
            accecount= (TextView)itemView.findViewById(R.id.accecount);
          acceownname= (TextView)itemView.findViewById(R.id.acceownname);
            accephoto= (ImageView) itemView.findViewById(R.id.accephoto);
        }
    }
}

