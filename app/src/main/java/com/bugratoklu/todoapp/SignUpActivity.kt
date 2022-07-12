package com.bugratoklu.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.editEmail
import kotlinx.android.synthetic.main.activity_main.signUpButton
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etEmail = findViewById(R.id.editEmailAct)
        etPassword = findViewById(R.id.editPasswordAct)

        auth = Firebase.auth

        signUpButton.setOnClickListener {
            signUpUser()

            if (editEmailAct.text.isNotEmpty() && editPasswordAct.text.isNotEmpty()) {

            } else {
                Toast.makeText(this, "Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }else
            {
                Toast.makeText(this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signUpAct(view: View) {



    }
}
