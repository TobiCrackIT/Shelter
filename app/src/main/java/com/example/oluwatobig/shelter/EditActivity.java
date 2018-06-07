package com.example.oluwatobig.shelter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oluwatobig.shelter.data.ShelterContract;
import com.example.oluwatobig.shelter.data.ShelterContract.ShelterEntry;
import com.example.oluwatobig.shelter.data.ShelterDBHelper;

public class EditActivity extends AppCompatActivity {

    private EditText mNameET,mBreedET,mWeightET;
    private Spinner mGenderSpinner;
    private int mGender=0;
    private String petName,petBreed;
    private int petGender,petWeight;

    private ShelterDBHelper shelterDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mNameET=(EditText)findViewById(R.id.name_editText);
        mBreedET=(EditText)findViewById(R.id.breed_editText);
        mWeightET=(EditText)findViewById(R.id.pet_weight_ET);

        mGenderSpinner=(Spinner)findViewById(R.id.genderSpinner);
        setSpinner();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                addItem();
                finish();
                return true;
            case R.id.delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setSpinner(){
        ArrayAdapter genderAA=ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        genderAA.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mGenderSpinner.setAdapter(genderAA);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.male))) {
                        mGender = ShelterContract.ShelterEntry.MALE;
                    } else if (selection.equals(getString(R.string.female))) {
                        mGender = ShelterContract.ShelterEntry.FEMALE; // Female
                    } else {
                        mGender =ShelterContract.ShelterEntry.UNKNOWN; // Unknown
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender=0;
            }
        });
    }

    private void addItem(){
        petName=mNameET.getText().toString().trim();
        petBreed=mBreedET.getText().toString().trim();
        //petGender=mGenderSpinner.getChildCount();
        petWeight=Integer.parseInt(mWeightET.getText().toString().trim());

        shelterDBHelper=new ShelterDBHelper(this);
        SQLiteDatabase sqLiteDatabase=shelterDBHelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(ShelterEntry.PET_NAME,petName);
        cv.put(ShelterEntry.BREED,petBreed);
        cv.put(ShelterEntry.WEIGHT,petWeight);
        cv.put(ShelterEntry.GENDER,mGender);

        long newEntry=sqLiteDatabase.insert(ShelterEntry.TABLE_NAME,null,cv);
        if (newEntry==-1)
            Toast.makeText(this,"Error occurred while creating item.",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"New Item added at ID "+newEntry,Toast.LENGTH_SHORT).show();
    }
}
