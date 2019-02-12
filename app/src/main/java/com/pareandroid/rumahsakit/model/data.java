package com.pareandroid.rumahsakit.model;

public class data {
    private String id, Nama, jenis_kelamin;

    public data (){
    }

    public data (String id, String Nama , String jenis_kelamin){
        this.id = id;
        this.Nama = Nama;
        this.jenis_kelamin = jenis_kelamin;

    }

    public String getId (){return id;}

    public  void setId (String id){this.id = id ;}

   public String getNama () {return Nama;}

    public  void setNama (String Nama){this.Nama = Nama ;}

    public String getJenis_kelamin (){return jenis_kelamin;}

    public  void setJenis_kelamin (String jenis_kelamin){this.jenis_kelamin = jenis_kelamin ;}

}
