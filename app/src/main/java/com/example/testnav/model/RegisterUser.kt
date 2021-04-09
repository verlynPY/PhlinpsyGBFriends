package com.example.testnav.model

import android.content.ContentValues.TAG
import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterUser {
    private lateinit var auth: FirebaseAuth
    private lateinit var reference: DatabaseReference

    fun Register(user: User){
        var SuccessFul: Boolean = true
        auth = FirebaseAuth.getInstance()
        reference = FirebaseDatabase.getInstance().reference



        auth.createUserWithEmailAndPassword(user.Email, user.PassWord)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val User: FirebaseUser = auth.currentUser!!
                        val Id = User.uid
                        val hashMap: HashMap<String, String> = HashMap()
                        hashMap.put("Id", Id)
                        hashMap.put("UserName", user.UserName.capitalize())
                        hashMap.put("NumberPhone", user.NumberPhone.toString())
                        hashMap.put("Age", user.Age.toString())
                        hashMap.put("Hobby", user.Hobby.capitalize())
                        hashMap.put("Latitude", user.Latitude.toString())
                        hashMap.put("Longitude", user.Longitude.toString())
                        hashMap.put("Email", user.Email)
                        hashMap.put("PassWord", user.PassWord)

                        reference.child("users").child(Id).setValue(hashMap)
                                .addOnCompleteListener { task ->
                            SuccessFul = true
                                    Log.e(TAG, "$task")
                                }

                                .addOnFailureListener { fail ->
                                    SuccessFul = false
                                    Log.e(TAG, "$fail")
                                }


                    }
                }
                .addOnFailureListener { fail ->
                    SuccessFul = false
                    Log.e(TAG, "$fail")
                }

           // return SuccessFul

    }

}