package com.example.smartmechanic.smart_mechanic;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartmechanic.smart_mechanic.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText editUsername,editName,editAddress,editPassword;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editName = (MaterialEditText)findViewById(R.id.editName);
        editUsername = (MaterialEditText)findViewById(R.id.editUsername);
        editAddress = (MaterialEditText)findViewById(R.id.editAddress);
        editPassword = (MaterialEditText)findViewById(R.id.editPassword);

        signUp = (Button)findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog(SignUp.this);
                mdialog.setMessage("Please waiting....");
                mdialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(editUsername.getText().toString()).exists()){
                            mdialog.dismiss();
                            Toast.makeText(SignUp.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }else{
                            mdialog.dismiss();
                            User user = new User(editUsername.getText().toString(),editPassword.getText().toString(),editName.getText().toString(),editAddress.getText().toString());
                            table_user.child(editUsername.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign up Successfully!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
