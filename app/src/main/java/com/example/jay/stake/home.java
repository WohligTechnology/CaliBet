package com.example.jay.stake;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class home extends AppCompatActivity{

    Button newBook;
    ListView lvBook;
    public String bookname= null;
    int[] bookId= new int[0];
    private ListActivity myActivity = new ListActivity();

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newBook = (Button) findViewById(R.id.btNewBook);
        lvBook = (ListView) findViewById(R.id.lvBookResult);

        final Intent inCreateBook = new Intent(this , createbook.class);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inCreateBook);
            }
        });

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;
                DatabaseHandler dba = new DatabaseHandler(home.this);
                bookname = (String) lvBook.getItemAtPosition(position);
                bookname = (String) lvBook.getItemAtPosition(position);

                Log.d("Bookname",bookname);

                int bi = dba.getBookIDString(bookname);

                Log.isLoggable("getID",bi);


                try
                {
                    Intent bookIntent = new Intent(home.this, OpenBook.class);
                    bookIntent.putExtra("BookName", bookname);
                    bookIntent.putExtra("BookId",bi);
                    startActivity(bookIntent);
                }
                catch(Exception e)
                {

                }
            }
        });

        List<Book> books = db.getAllBooks();
        String log=null;
        String temp=null;
        String[] bookList= new String[books.size()];
        bookId= new int[books.size()];
        int size= 0;
        for (Book b : books) {
            temp = log;
            bookList[size]= b.getBookName();
            bookId[size]= b.getID();
            log = "\n"+bookList[size];
            Log.d("Name: ", bookList[size]);
            log=temp+log;
            size++;

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(home.this, android.R.layout.simple_list_item_1, bookList);
        lvBook.setAdapter(adapter);
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        DatabaseHandler db = new DatabaseHandler(this);
        List<Book> books = db.getAllBooks();
        String log=null;
        String temp=null;
        String[] bookList= new String[books.size()];
        int size=0;
        for (Book b : books) {
            temp = log;
            bookList[size]= b.getBookName();
            log = "\n"+bookList[size];
            Log.d("Name: ", bookList[size]);
            log=temp+log;
            size++;

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(home.this, android.R.layout.simple_list_item_1, bookList);

        lvBook.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
