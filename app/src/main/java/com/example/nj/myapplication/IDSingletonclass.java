package com.example.nj.myapplication;

/**
 * Created by NJ on 2015-10-27.
 */
public class IDSingletonclass {

    public static IDSingletonclass OBJECT;
    public String ID;

    public IDSingletonclass() {

    }


    public IDSingletonclass(String id) {
        this.ID = id;
    }

    public String get_ID() {
        return this.ID;
    }



    public static IDSingletonclass getInstance()
    {
        if(OBJECT==null)
        {
            OBJECT = new IDSingletonclass();
        }
        return OBJECT;
    }

}
