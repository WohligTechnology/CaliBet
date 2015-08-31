package com.example.jay.stake;


import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "stake";
 
    // Book table name
    private static final String TABLE_BOOK = "book";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BOOKNAME = "name";
    private static final String KEY_HORSE_ONE = "horse1";
    private static final String KEY_HORSE_TWO = "horse2";
    private static final String KEY_DATE = "date";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BOOKNAME + " TEXT,"
                + KEY_HORSE_ONE + " TEXT," + KEY_HORSE_TWO + " TEXT,"+KEY_DATE + " TEXT"+ ")";


        db.execSQL(CREATE_BOOK_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_BOOKNAME, book.getBookName());
        values.put(KEY_HORSE_ONE, book.getHorse1());
        values.put(KEY_HORSE_TWO, book.getHorse2());
        values.put(KEY_DATE, book.getDate());
 
        // Inserting Row
        db.insert(TABLE_BOOK, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_BOOK, new String[] { KEY_ID,
                KEY_BOOKNAME, KEY_HORSE_ONE, KEY_HORSE_TWO, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Book book = new Book(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
        cursor.getString(3), cursor.getString(4));
        // return contact
        return book;
    }
 
    // Getting All Contacts
    public List<Book> getAllBooks() {
        List<Book> contactList = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOK;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setID(Integer.parseInt(cursor.getString(0)));
                book.setBookName(cursor.getString(1));
                book.setHorse1(cursor.getString(2));
                book.setHorse2(cursor.getString(3));
                book.setDate(cursor.getString(4));


                // Adding contact to list
                contactList.add(book);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    public int updateContact(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_BOOKNAME, book.getBookName());
        values.put(KEY_HORSE_ONE, book.getHorse1());
        values.put(KEY_HORSE_TWO, book.getHorse2());
        values.put(KEY_DATE, book.getDate());
 
        // updating row
        return db.update(TABLE_BOOK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(book.getID()) });
    }
 
    // Deleting single contact
    /*public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, KEY_ID + " = ?",
                new String[] { String.valueOf(book.getID()) });
        db.close();
    }*/

    public void deleteBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, KEY_ID + " = ?",
                new String[] { String.valueOf(book.getID()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getBookCount() {
        String bookQuery = "SELECT  * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(bookQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }

    Book getBookID(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOK, new String[]{KEY_ID,
                        KEY_BOOKNAME, KEY_HORSE_ONE, KEY_HORSE_TWO, KEY_DATE}, KEY_BOOKNAME + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();



        Book book = new Book(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
        // return contact
        return book;
    }

    int getBookIDString(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOK, new String[] { KEY_ID,
                        KEY_BOOKNAME, KEY_HORSE_ONE, KEY_HORSE_TWO, KEY_DATE }, KEY_BOOKNAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        int a =Integer.parseInt(cursor.getString(0));

        Book book = new Book(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(1), cursor.getString(2));
        // return contact

        return a;
        //return book;
    }
 
}