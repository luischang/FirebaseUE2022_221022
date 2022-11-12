package dev.lchang.firebaseue2022.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.lchang.firebaseue2022.R
import dev.lchang.firebaseue2022.ui.fragments.adapter.MascotaAdapter
import dev.lchang.firebaseue2022.ui.fragments.client.MascotaClient
import dev.lchang.firebaseue2022.ui.fragments.model.MascotaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_mascota, container, false)

        val rvMascota: RecyclerView = view.findViewById(R.id.rvMascota)
        rvMascota.layoutManager = LinearLayoutManager(view.context)
        var call: Call<List<MascotaModel>> = MascotaClient.retrofitService.listarMascota()

        call.enqueue(object: Callback<List<MascotaModel>>{
            override fun onResponse(
                call: Call<List<MascotaModel>>,
                response: Response<List<MascotaModel>>
            ) {
                rvMascota.adapter = MascotaAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<MascotaModel>>, t: Throwable) {
                Snackbar.make(view, "Ocurrió un error al obtener las mascotas",Snackbar.LENGTH_LONG).show()
            }


        })


        return view
    }
}