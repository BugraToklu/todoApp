package com.bugratoklu.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var emailEdit: EditText
    lateinit var passwordEdit: EditText
    lateinit var btnLogin: Button
    lateinit var btnKayıt : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.signInButton)
        emailEdit = findViewById(R.id.editEmail)
        passwordEdit = findViewById(R.id.editPassword)
        btnKayıt = findViewById(R.id.signUpButton)

        auth = FirebaseAuth.getInstance()

        btnKayıt.setOnClickListener {

            val intentToSignUp = Intent(this,SignUpActivity::class.java)
            startActivity(intentToSignUp)

        }

        btnLogin.setOnClickListener {
            if (editEmail.text.toString().trim().isNotEmpty() && editPassword.text.toString().trim().isNotEmpty()){
                    login()
            }else
            {
                Toast.makeText(this,"Lütfen Boş Alanları Doldurunuz",Toast.LENGTH_LONG).show()
            }


        }

    }
    private fun login(){
        val email = emailEdit.text.toString()
        val password = passwordEdit.text.toString()

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Başarıyla Giriş Yapıldı",Toast.LENGTH_LONG).show()
                    val intentToDo = Intent(this,toDoActivity::class.java)
                    startActivity(intentToDo)
            }else
            {
                Toast.makeText(this,"Giriş Başarısız",Toast.LENGTH_LONG).show()
            }
        }
    }

}
