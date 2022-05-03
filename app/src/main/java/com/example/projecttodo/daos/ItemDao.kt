package com.example.projecttodo.daos

import com.example.projecttodo.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ItemDao {

    private val db = FirebaseFirestore.getInstance()// get db instance
    val itemCollection = db.collection("Items")// create items collections

    // we alse need auth for currentUserId
    private val auth = FirebaseAuth.getInstance()
    fun addItem(text: String) {
        val currentUserId = auth.currentUser!!.uid
        val item = Item(text, currentUserId)
        itemCollection.document().set(item)

    }
}