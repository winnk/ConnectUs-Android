package com.example.kaya.connectus2.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaya.connectus2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

        public static final String TAG = LoginActivity.class.getSimpleName();

        @Bind(R.id.passwordLoginButton)
        Button mPasswordLoginButton;
        @Bind(R.id.emailEditText)
        EditText mEmailEditText;
        @Bind(R.id.passwordEditText) EditText mPasswordEditText;
        @Bind(R.id.registerTextView) TextView mRegisterTextView;
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;
        private ProgressDialog mAuthProgressDialog;

@Override
   protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    mAuth = FirebaseAuth.getInstance();
    mPasswordLoginButton.setOnClickListener(this);
    mRegisterTextView.setOnClickListener(this);
    createAuthProgressDialog();
    mAuthListener = new FirebaseAuth.AuthStateListener() {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
    };
}

private void createAuthProgressDialog() {
    mAuthProgressDialog = new ProgressDialog(this);
    mAuthProgressDialog.setTitle("Loading...Standby");
    mAuthProgressDialog.setMessage("Firebase autoriziation in process..");
    mAuthProgressDialog.setCancelable(false);
} // end auth progresssx@

private void loginWithPassword() {
    String email = mEmailEditText.getText().toString().trim();
    String password = mPasswordEditText.getText().toString().trim();
    if (email.equals("")) {
        mEmailEditText.setError("Your email address");
        return;
    }
    if (password.equals("")) {
        mPasswordEditText.setError("Password required");
        return;
    } // end loginWithPassword

    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail", task.getException());
                        Toast.makeText(LoginActivity.this, "Try again. Auth failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

@Override
public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
} // end start

@Override
public void onStop() {
    super.onStop();
    if (mAuthListener != null) {
        mAuth.removeAuthStateListener(mAuthListener);
    }
} // end stop

@Override
public void onClick(View view) {
    if (view == mPasswordLoginButton){
        loginWithPassword();
    }
    if (view == mRegisterTextView) {
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }

}


}

