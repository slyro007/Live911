package com.github.slyro007.nuhack91;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editTextRoomName;
    Button buttonSubmit, buttonNextPage;
    Spinner spinnerNameB;

    DatabaseReference databaseLocations;

    ListView listViewLocations;

    List<Location> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        editTextRoomName= (EditText) findViewById(R.id.editTextRoomName);
        buttonSubmit= (Button) findViewById(R.id.buttonSubmit);
        spinnerNameB= (Spinner) findViewById(R.id.spinnerNameB);

        listViewLocations = (ListView) findViewById(R.id.listViewLocations);

        locationList = new ArrayList<>();
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addLocation();

            }
        });

        /*buttonNextPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this, com.github.slyro007.nuhack91.Main2Activity.class);
                startActivity(intent);


            }
       }); */


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseLocations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                locationList.clear();

                for(DataSnapshot locationSnapshot : dataSnapshot.getChildren()){
                        Location location = locationSnapshot.getValue(Location.class);

                        locationList.add(location);

                }

                LocationList adapter = new LocationList(MainActivity.this, locationList);
                listViewLocations.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addLocation(){
        String room= editTextRoomName.getText().toString().trim();
        String building = spinnerNameB.getSelectedItem().toString();

        if(!TextUtils.isEmpty(room)){

            String id = databaseLocations.push().getKey();

            Location location = new Location(id, room, building);

            databaseLocations.child(id).setValue(location);

            Toast.makeText(this, "Location Recorded", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please enter room number", Toast.LENGTH_LONG).show();
        }
    }
}
