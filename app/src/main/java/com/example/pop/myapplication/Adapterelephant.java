package com.example.pop.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapterelephant  extends RecyclerView.Adapter<Adapterelephant.MyViewHolder> {
    List<Beanelephant> beanlist=null;
    Context c=null;


    public Adapterelephant(List<Beanelephant> beanlist, Viewelepdetail viewelepdetail) {
        this.beanlist=beanlist;
        c=viewelepdetail;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_elephant,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Beanelephant b = beanlist.get(position);
        holder.elname.setText(b.getElephantname());
        holder.elheight.setText(b.getElephantheight());
       // holder.elrate.setText(b.getElephantname());
       // holder.elownname.setText(b.getElephantheight());
        holder.elrate.setText(b.getElephantrate());
        holder.elownname.setText(b.getElephantownername());
        Log.d("_accessoriesphoto",b.getElephantphoto());
String url="http://192.168.43.233:8084/myelephant/ElephantOwner/image/"+b.getElephantphoto();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Beanelephant be=beanlist.get(getAdapterPosition());

                    String elename=be.getElephantname();
                    String eleid=be.getId();

                    Intent in=new Intent(c.getApplicationContext(),ElephantBooking.class);
                    in.putExtra("elephantid",eleid);
                    c.startActivity(in);



                }
            });
        }
    }


}
