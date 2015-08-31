package com.example.jay.stake;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class createbook extends AppCompatActivity {

    Button btCreate;
    EditText h1, h2, bn;
    DatePicker dpd;
    TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createbook);

        h1 = (EditText) findViewById(R.id.etHorse1);
        h2 = (EditText) findViewById(R.id.etHorse2);
        bn = (EditText) findViewById(R.id.etName);
        dpd = (DatePicker) findViewById(R.id.dpDate);
        tvRes = (TextView) findViewById(R.id.tvResult);
        btCreate = (Button) findViewById(R.id.btNewBook);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_createbook, menu);
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

    public void createBook(View v) {
        String name = bn.getText().toString();
        String horse1 = h1.getText().toString();
        String horse2 = h2.getText().toString();
        int day = dpd.getDayOfMonth();
        int month = dpd.getMonth() + 1;
        int year = dpd.getYear();
        String date = day + "/" + month + "/" + year;

        DatabaseHandler db = new DatabaseHandler(this);
        //db.addBook(new Book(1,"Ravi", "h1","h2","1122"));
        db.addBook(new Book(name, horse1, horse2, date));
        Log.d("Reading: ", "Reading all book..");
        List<Book> books = db.getAllBooks();

        for (Book b : books) {
            String log = "Id: " + b.getID() + " ,Book Name: " + b.getBookName() + " ,Horse1: " + b.getHorse1() + " ,Horse2: " + b.getHorse2() + " ,Date: " + b.getDate();
            //String log = "Id: " + b.getID() + " ,Book Name: " + b.getBookName() + " ,Horse1: " + b.getHorse1();
            // Writing Contacts to log
            Log.d("Name: ", log);
            //tvRes.setText("New Book Create with name " + name + "\n" + horse1 + " & " + horse2 + "\n On " + day + "/" + month + "/" + year);
            tvRes.setText(log);
        }
    }
}