package dev.lchang.firebaseue2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var db = FirebaseAuth.getInstance()
        val txtEmail: EditText = findViewById(R.id.txtEmail)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val correo = txtEmail.text.toString()
            val clave = txtPassword.text.toString()

            db.signInWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Inicio satisfactorio",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }else{
                        Toast.makeText(this,"Correo o clave incorrecta",Toast.LENGTH_LONG).show()
                    }
                }

                }



    }
}