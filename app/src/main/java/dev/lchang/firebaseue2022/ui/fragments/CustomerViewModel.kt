package dev.lchang.firebaseue2022.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dev.lchang.firebaseue2022.ui.fragments.database.CustomerEntity
import dev.lchang.firebaseue2022.ui.fragments.database.CustomerRepository

class CustomerViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = CustomerRepository(application)
    val customers = repository.getCustomers()

    fun saveCustomer(customerEntity: CustomerEntity){
        repository.insert(customerEntity)
    }
}