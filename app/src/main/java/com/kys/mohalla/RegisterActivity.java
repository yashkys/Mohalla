package com.kys.mohalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    Button Register, SendOtp;
    TextView Login;
    EditText RegisterPhoneNumber, RegisterOtp;

    String VerificationId;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register= findViewById(R.id.btnRegister);
        Login = findViewById(R.id.tvLogin);
        SendOtp = findViewById(R.id.btnSendOtp);
        RegisterPhoneNumber = findViewById(R.id.editTextRegisterPhoneNumber);
        RegisterOtp = findViewById(R.id.editTextRegisterOtp);

        SendOtp.setOnClickListener(view->{
            if(TextUtils.isEmpty(RegisterPhoneNumber.getText().toString()) || (RegisterPhoneNumber.getText().toString()).length()<10){
                Toast.makeText(this, "Enter valid Phone Number", Toast.LENGTH_SHORT).show();
            }
            else{
                String number = RegisterPhoneNumber.getText().toString();
                sendVerificationCode(number);
            }

        });

        Login.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        Register.setOnClickListener(view->{
            if(TextUtils.isEmpty(RegisterOtp.getText().toString()) ){
                Toast.makeText(this, "Wrong OTP entered...", Toast.LENGTH_SHORT).show();
            }
            else {
                verifyOtp(RegisterOtp.getText().toString());
            }
        });
    }

    private void verifyOtp(String code) {
        PhoneAuthCredential credential  = PhoneAuthProvider.getCredential(VerificationId, code);
        signupbyCredential(credential);
    }

    private void signupbyCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) Toast.makeText(this, "Verification Completed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null) {
                verifyOtp(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(RegisterActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(verificationId, token);
            VerificationId = verificationId;
            Toast.makeText(RegisterActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();
            Register.setEnabled(true);
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser =  FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    }