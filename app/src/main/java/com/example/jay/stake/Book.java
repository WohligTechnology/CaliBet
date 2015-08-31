package com.example.jay.stake;

public class Book {

    //private variables
    int _id;
    String _book_name;
    String _horse1;
    String _horse2;
    String _date;

    // Empty constructor
    public Book(){

    }
    // constructor
    public Book(int id, String book_name, String horse1,String horse2, String date){
        this._id = id;
        this._book_name = book_name;
        this._horse1 = horse1;
        this._horse2 = horse2;
        this._date=date;
    }

    // constructor
    public Book(String book_name, String horse1, String horse2, String date){
        this._book_name = book_name;
        this._horse1 = horse1;
        this._horse2 = horse2;
        this._date = date;
    }


    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting book name
    public String getBookName(){

        return this._book_name;
    }

    // setting book name
    public void setBookName(String name){

        this._book_name = name;
    }
    // setting horse1
    public void setHorse1(String horse1){

        this._horse1 = horse1;
    }
    //getting horse1
    public String getHorse1(){

        return this._horse1;
    }

    // setting horse2
    public void setHorse2(String horse2){

        this._horse2 = horse2;
    }
    //getting horse2
    public String getHorse2(){

        return this._horse2;
    }

    // getting book name
    public String getDate(){

        return this._date;
    }

    // setting book name
    public void setDate(String date){

        this._date = date;
    }
}