package com.example.testnav.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testnav.model.RegisterUser
import com.example.testnav.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel: ViewModel() {

    private lateinit var registerUser: RegisterUser

    fun SaveUsers(user: User){
        registerUser = RegisterUser()

        if(user.UserName.isNullOrEmpty() || user.NumberPhone.equals(0) ||
                (user.Age >=100 || user.Age <= 12) ||
                user.Hobby.isNullOrEmpty() ||
                user.Email.isNullOrEmpty() ||
                user.PassWord.isNullOrEmpty()
        ){
            viewModelScope.launch(Dispatchers.IO){
                registerUser.Register(user)
            }
        }
        else{
            println("There are field empty")
        }




    }

}