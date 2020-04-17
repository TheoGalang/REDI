package com.example.redi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText email;
    EditText pass;
    TextView login;
    TextView lupa;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_isiusernamelogin);
        pass = findViewById(R.id.et_isipasswordlogin);
        login = findViewById(R.id.tv_daftar);
        lupa = findViewById(R.id.tv_lupapassword);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this, "Berhasil login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, ProfilDataActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Silahkan Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emaillogin = email.getText().toString();
                String password = pass.getText().toString();
                if (emaillogin.isEmpty()) {
                    email.setError("Silahkan Masukkan Email");
                    email.requestFocus();
                } else if (password.isEmpty()) {
                    pass.setError("Silahkan Isi Password");
                    pass.requestFocus();
                } else if (emaillogin.isEmpty() && password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Isi email dan password terlebih dahulu!", Toast.LENGTH_SHORT).show();
                } else if (!(emaillogin.isEmpty() && password.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(emaillogin,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Eror", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent emailLogin = new Intent(LoginActivity.this, ProfilDataActivity.class);

                                String []temp = emaillogin.split("@",2);
                                String temp2 = (temp[0]+temp[1]).split("\\.",2)[0];
                                emailLogin.putExtra("email",temp2);
                            }
                        }
                    });
                    lupa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent forget = new Intent(LoginActivity.this,ProfilDataActivity.class);
                            startActivity(forget);
                        }
                    });

                }

            }
        });
    }
    }



//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String emaillogin = email.getText().toString();
//                String password = pass.getText().toString();
//                if (emaillogin.isEmpty()) {
//                    email.setError("Silahkan Masukkan Email");
//                    email.requestFocus();
//                } else if (password.isEmpty()) {
//                    pass.setError("Silahkan Isi Password");
//                    pass.requestFocus();
//                } else if (emaillogin.isEmpty() && password.isEmpty()) {
//                    Toast.makeText(LoginActivity.this, "Isi email dan password terlebih dahulu!", Toast.LENGTH_SHORT).show();
//                } else if (!(emaillogin.isEmpty() && password.isEmpty())) {
//                    mAuth.createUserWithEmailAndPassword(emaillogin, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()) {
//                                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
//                            } else {
//                                startActivity(new Intent(LoginActivity.this, ProfilDataActivity.class));
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//}