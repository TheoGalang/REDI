package com.example.redi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfilDataActivity extends AppCompatActivity {

    TextView isi_nama, isi_username, isi_no, isi_email, isi_nik, isi_kategori;
    DatabaseReference mRef;
    String emailUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_data);

        Intent intent = getIntent();
        emailUser = intent.getStringExtra("email");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavbar);
        bottomNavigationView.setSelectedItemId(R.id.home_button);
        bottomNavigationView.setSelectedItemId(R.id.edit_button);
        bottomNavigationView.setSelectedItemId(R.id.status_button);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_button:
                        startActivity(new Intent(ProfilDataActivity.this,MainActivity.class));
                        return true;
                    case R.id.status_button:
                        startActivity(new Intent(ProfilDataActivity.this,ProfilDataActivity.class));
                        return true;
                }
                return false;
            }
        });

        isi_nama = findViewById(R.id.tv_isinama);
        isi_username = findViewById(R.id.tv_isiusername);
        isi_no = findViewById(R.id.tv_isino);
        isi_email = findViewById(R.id.tv_isiemail);
        isi_nik = findViewById(R.id.tv_isinik);
        isi_kategori = findViewById(R.id.tv_isikategori);

        mRef = FirebaseDatabase.getInstance().getReference().child("User").child(emailUser);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                GenericTypeIndicator<User> t = new GenericTypeIndicator<User>(){};
                User user = dataSnapshot.getValue(t);

//                String nama = dataSnapshot.child("nama").getValue().toString();
//                String username = dataSnapshot.child("username").getValue().toString();
//                String nomor = dataSnapshot.child("nomor").getValue().toString();
//                String email = dataSnapshot.child("email").getValue().toString();
//                String nik = dataSnapshot.child("nik").getValue().toString();
//                String kategori = dataSnapshot.child("kategori").getValue().toString();

                isi_nama.setText(user.getName());
                isi_username.setText(user.getUsername());
                isi_no.setText(user.getNoHp());
                isi_email.setText(user.getEmail());
                .setText();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
