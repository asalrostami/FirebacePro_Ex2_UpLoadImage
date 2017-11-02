package com.example.asal.firebacepro_ex2_uploadimage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddToDatabase extends AppCompatActivity {

    private static final String TAG = "AddToDatabase";

    private EditText mEditTextNewFood;
    private Button mButtonAdd;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_database);

        mAuth = FirebaseAuth.getInstance();

        // Write a message to the database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        //
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(AddToDatabase.this,"successfully signed in",Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(AddToDatabase.this,"successfully signed out",Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };



        mEditTextNewFood = (EditText)findViewById(R.id.editTextAddNewFood);
        mButtonAdd = (Button)findViewById(R.id.btnAdd);
//        mButtonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newFood = mEditTextNewFood.getText().toString();
//                if(!newFood.equals(""))
//                {
//                    //getting current user id as a key
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    String userId = user.getUid();
//                    myRef.child(userId).child("Food").child("Favorite Foods").child(newFood).setValue("true");
//                    mEditTextNewFood.setText("");
//                }
//                else
//                {
//                    Toast.makeText(AddToDatabase.this,"Enter A Food",Toast.LENGTH_SHORT).show();
//                }
//                //finish();
//            }
//        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //important should be change to object
                Object value = dataSnapshot.getValue();
                //
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }

        });
        //

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void addToDatabase(View view) {
        String newFood = mEditTextNewFood.getText().toString();
                if(!newFood.equals(""))
                {
                    //getting current user id as a key
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userId = user.getUid();
                    myRef.child(userId).child("Food").child("Favorite Foods").child(newFood).setValue("true");
                    mEditTextNewFood.setText("");
                }
                else
                {
                    Toast.makeText(AddToDatabase.this,"Enter A Food",Toast.LENGTH_SHORT).show();
                }
                //finish();
    }
}
