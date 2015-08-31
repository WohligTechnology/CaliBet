package com.example.jay.stake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import java.util.List;

public class OpenBook extends AppCompatActivity {

    TextView tvBookname;
    Button btDeleteBook;
    String bookName;
    int bookId;
    ListView lvHorse;
    DatabaseHandler db =new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_book);

        tvBookname = (TextView) findViewById(R.id.tvBookName);
        btDeleteBook =(Button) findViewById(R.id.btDeleteBook);
        lvHorse = (ListView) findViewById(R.id.lvHorse);

        //Get the previous activity values
        Intent thisIntent = getIntent();
        bookName = thisIntent.getStringExtra("BookName");
        Bundle extras = getIntent().getExtras();
        bookId = extras.getInt("BookId");

        // set the textbox witht the bookname
        tvBookname.setText(bookName);

        //get the contents of book by passing the bookid
        Book horseList = db.getBook(bookId);

        //make a array of string of the names of horse to make a listView
        final String[] horse = new String[2];

        horse[0]= horseList.getHorse1();
        horse[1]= horseList.getHorse2();

        //set the listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(OpenBook.this, android.R.layout.simple_list_item_1,horse);
        lvHorse.setAdapter(adapter);


        //on item click listener
        lvHorse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try
                {
                    // make an intent to open new activity and send the values
                    Intent makeBet = new Intent(OpenBook.this, Bet.class);
                    makeBet.putExtra("horse1",horse[0]);
                    makeBet.putExtra("horse2",horse[1]);
                    makeBet.putExtra("bookName",bookName);
                    makeBet.putExtra("bookId",bookId);
                    startActivity(makeBet);
                }
                catch(Exception e)
                {

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_book, menu);
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

    public void removeBook(View v)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Inside deleteBook","Inside deleteBook");
        int id=0;
        String bookname = bookName;
        String h1 = null;
        String h2 = null;
        String date= null;
        String KEY_ID = "id";
        Book b =db.getBookID(bookname);
        id= b.getID();
        Log.isLoggable("BookID", id);
        db.deleteBook(new Book(id, bookname, "", "", ""));
        Log.d("Deleting: ", "Deleted book..");

        Intent backHome = new Intent(this,home.class);
        startActivity(backHome);
    }

}
