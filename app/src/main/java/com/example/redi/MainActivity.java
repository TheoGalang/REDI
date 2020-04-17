package com.example.redi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    CircleImageView foto;
    TextView name_vacancies, location_vacancies, duration_vacancies;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_vacancies = findViewById(R.id.tv_name_vacancy);
        location_vacancies = findViewById(R.id.tv_vacancy_location);
        duration_vacancies = findViewById((R.id.tv_vacancy_duration));
        foto = findViewById(R.id.event_photo);
        mref = FirebaseDatabase.getInstance().getReference().child("Vacancies");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.child("event").getValue().toString();
                String location = dataSnapshot.child("location").getValue().toString();
                String duration = dataSnapshot.child("duration").getValue().toString();
                name_vacancies.setText(nama);
                location_vacancies.setText(location);
                duration_vacancies.setText(duration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
