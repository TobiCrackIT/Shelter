package com.example.oluwatobig.shelter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.oluwatobig.shelter.data.ShelterContract;
import com.example.oluwatobig.shelter.data.ShelterContract.ShelterEntry;
import com.example.oluwatobig.shelter.data.ShelterDBHelper;

public class MainActivity extends AppCompatActivity {

    private ShelterDBHelper shelterDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton catalogFab=(FloatingActionButton)findViewById(R.id.fab);
        catalogFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEdit=new Intent(MainActivity.this,EditActivity.class);
                startActivity(iEdit);
            }
        });

        shelterDBHelper=new ShelterDBHelper(this);

        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catalog_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            case R.id.delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPet(){
        SQLiteDatabase sqLiteDatabase=shelterDBHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(ShelterEntry.PET_NAME,"Toto");
        contentValues.put(ShelterEntry.BREED,"Terrier");
        contentValues.put(ShelterEntry.GENDER,ShelterEntry.MALE);
        contentValues.put(ShelterEntry.WEIGHT,7);

        long newRowID=sqLiteDatabase.insert(ShelterEntry.TABLE_NAME,null,contentValues);
        Log.v("MainActivity","New row added");
    }

    private void displayDatabaseInfo(){
        Log.e("HELLO","HELLO");

        SQLiteDatabase sqLiteDatabase=shelterDBHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ ShelterContract.ShelterEntry.TABLE_NAME,null);

        try{
            TextView catalogTextView=(TextView)findViewById(R.id.catalog_text_view);
            catalogTextView.setText("Number of rows in shelter db table: "+cursor.getCount());
        }finally {
            cursor.close();
        }
    }
}
