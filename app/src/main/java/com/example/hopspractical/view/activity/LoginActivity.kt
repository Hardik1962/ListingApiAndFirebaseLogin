package com.example.hopspractical.view.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hopspractical.R
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthOptions
import java.util.concurrent.TimeUnit
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import android.content.Intent
import android.util.Log
import android.view.View
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener


class LoginActivity : AppCompatActivity() {

    // variable for FirebaseAuth class
    lateinit var auth: FirebaseAuth

    private var number = ""
    private var otp = ""
    // string for storing our verification ID
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            number = etNumber.text.toString()
            otp = etOtp.text.toString()
            if (number.isEmpty()){
                Toast.makeText(this,"Enter Phone Number.",Toast.LENGTH_SHORT).show()
            }else if(number.length < 10){
                Toast.makeText(this,"Enter valid phone no",Toast.LENGTH_SHORT).show()
            }
            else if (otp.isEmpty()){
                Toast.makeText(this,"Enter otp.",Toast.LENGTH_SHORT).show()
            }else{
                verifyCode(otp)
            }

            tvOtp.setOnClickListener {
                if (number.length <10){
                    Toast.makeText(this,"Enter valid phone no",Toast.LENGTH_SHORT).show()
                }else{
                    progress.visibility = View.VISIBLE
                    val phone = "+91" + etNumber.text.toString()
                    sendVerificationCode(phone)
                }
            }

            callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                // This method is called when the verification is completed
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    val code =  credential.smsCode
                    if (code != null){
                        Log.e("code",code)
                        etOtp.setText(code)
                        verifyCode(code)
                    }
                    Log.e("sucesss" , "onVerificationCompleted Success")
                }

                // Called when verification is failed add log statement to see the exception
                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("failed" , "onVerificationFailed  $e")
                }

                // On code is sent by the firebase this method is called
                // in here we start a new activity where user can enter the OTP
                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d("GFG","onCodeSent: $verificationId")
                    storedVerificationId = verificationId
                    resendToken = token

                    // Start a new activity using intent
                    // also send the storedVerificationId using intent
                    // we will use this id to send the otp back to firebase

                }
            }

        }
    }

    private fun sendVerificationCode(number: String) {
        // this method is used for getting
        // OTP on user phone number.
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        progress.visibility = View.GONE
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(code:String){
        val credentialdata = PhoneAuthProvider.getCredential(storedVerificationId, code)
        signInWithCredential(credentialdata)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        progress.visibility =  View.VISIBLE
        auth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    progress.visibility = View.GONE
                    // if the code is correct and the task is successful
                    // we are sending our user to new activity.
                    val i = Intent(this@LoginActivity,PatientsListActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    // if the code is not correct then we are
                    // displaying an error message to the user.
                    Toast.makeText(
                        this@LoginActivity,
                        task.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

}