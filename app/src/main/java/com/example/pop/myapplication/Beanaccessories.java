package com.example.pop.myapplication;

import android.util.Log;

public class Beanaccessories
{
    String id;
    String accessoriesname;
    String accessoriesrate;
    String accessoriescount;
    String accessoriesphoto;
    String accessoriesownername;

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }



    public String getAccessoriesname() {
        return accessoriesname;
    }

    public String setAccessoriesname(String accessoriesname)
    {
        this.accessoriesname=accessoriesname;
        return accessoriesname;
    }
    public String setAccessoriesrate(String accessoriesrate)
    {
        this.accessoriesrate=accessoriesrate;
        Log.d("alliswellrate",accessoriesrate);
        return accessoriesrate;
    }
    public String setAccessoriescount(String accessoriescount)
    {
        this.accessoriescount=accessoriescount;
        return accessoriescount;
    }
    public String setAccessoriesphoto(String accessoriesphoto)
    {
        this.accessoriesphoto=accessoriesphoto;
        return accessoriesphoto;
    }
    public String setAccessoriesownername(String accessoriesownername)
    {
        this.accessoriesownername=accessoriesownername;
        return accessoriesownername;
    }
    public String getAccessoriesrate() {
        return accessoriesrate;
    }

    public String getAccessoriescount() {

        return accessoriescount;
    }



    public String getAccessoriesphotophoto() {
        return accessoriesphoto;
    }


    public String getAccessoriesownername() {
        return accessoriesownername;
    }
}
