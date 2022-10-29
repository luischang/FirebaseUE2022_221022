package dev.lchang.firebaseue2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()
        val tvCurso: TextView = findViewById(R.id.tvCurso)
        val tvNota: TextView = findViewById(R.id.tvNota)

        db.collection("courses")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
                    Log.w("Firebase","Error al consultar cursos")
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED ->{
                            Log.d("Firebase","Data: " + dc.document.data)
                            tvCurso.text = dc.document.data["description"].toString()
                            tvNota.text = dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.REMOVED -> {
                            Log.d("Firebase","Removed data")
                        }
                     /*   DocumentChange.Type.MODIFIED ->{
                            Log.d("Firebase","Data: " + dc.document.data)
                            tvCurso.text = dc.document.data["description"].toString()
                            tvNota.text = dc.document.data["score"].toString()
                        }*/
                        else -> {
                            Log.e("Firebase","Error in document")
                        }
                    }

                }


            }


    }
}