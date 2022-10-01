package com.ncs.drdocs

import android.R.attr.phoneNumber
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.ncs.drdocs.MainModels.MainHostActivity
import com.ncs.drdocs.MainModels.OtpActivity
import com.ncs.drdocs.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var _binding: ActivityMainBinding? = null
    val binding get() : ActivityMainBinding = _binding!!
    lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    lateinit var pref : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        pref = getSharedPreferences("user", Context.MODE_PRIVATE)

        val authId: String? = pref.getString("authID",null)
        Log.d("Auth id ", "onCreate: Auth id : "+ authId)

        if (authId != null){
            startActivity(Intent(applicationContext, MainHostActivity::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()
        binding.buttonNext.setOnClickListener(this)



        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, MainHostActivity::class.java))
                finish()
                Log.d("GFG", "onVerificationCompleted Success")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("GFG", "onVerificationFailed  $e")
            }


            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d("GFG", "onCodeSent: $verificationId")
                storedVerificationId = verificationId
                resendToken = token
                Toast.makeText(baseContext,"OTP sent, hold tight", Toast.LENGTH_SHORT).show()

                val intent = Intent(applicationContext, OtpActivity::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
                finish()
            }
        }


    }


    override fun onClick(p0: View?) {


        if (!binding.numberTv.text.isEmpty()) {
            val phoneNumber: String = binding.numberTv.text.toString().trim()
            sendOTP("+91" + phoneNumber)

        } else {
            Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show()
        }

    }


    fun sendOTP(phone: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()

        Toast.makeText(this,"Processing your request!", Toast.LENGTH_SHORT).show()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}
