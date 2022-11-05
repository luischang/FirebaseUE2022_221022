package dev.lchang.firebaseue2022.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dev.lchang.firebaseue2022.R
import dev.lchang.firebaseue2022.ui.fragments.adapter.CourseAdapter
import dev.lchang.firebaseue2022.ui.fragments.model.CourseModel

class ListadoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_listado, container, false)
        val db = FirebaseFirestore.getInstance()
        val lstCourses: ArrayList<CourseModel> = ArrayList()
        val rvCourse: RecyclerView = view.findViewById(R.id.rvCourse)

        db.collection("courses")
            .addSnapshotListener{snap,e->
                if(e!=null){
                    Log.e("Firebase error","Error al listar los cursos")
                    return@addSnapshotListener
                }

                for(dc in  snap!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED,
                        DocumentChange.Type.MODIFIED,
                        DocumentChange.Type.REMOVED -> {
                            lstCourses.add(
                                          CourseModel(dc.document.data["description"].toString(),
                                           dc.document.data["score"].toString()))
                        }
                    }

                }
                rvCourse.adapter = CourseAdapter(lstCourses)
                rvCourse.layoutManager = LinearLayoutManager(requireContext())

            }
        return view
    }
}