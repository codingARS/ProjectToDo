package com.example.projecttodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttodo.daos.ItemDao
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class MainActivity : AppCompatActivity() {

    private companion object{
        private const val TAG = "SignInActivity"
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var itemDao: ItemDao
    private lateinit var auth: FirebaseAuth
    private lateinit var adaptor: RVadaptor
    private lateinit var signOutBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.fab)

        itemDao = ItemDao()
        auth = FirebaseAuth.getInstance()




        fab.setOnClickListener{
            // navigate to new activity to add items
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }

        signOutBtn = findViewById(R.id.signOutBtn)
        signOutBtn.setOnClickListener {
            auth.signOut()
            mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, OnCompleteListener {
                    signOutFun()
                })
        }
        setUpRecyclerView()

    }

    private fun signOutFun() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Successfully signed out", Toast.LENGTH_LONG).show()
    }


    private fun setUpRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        // get itemCollection collection from itemDao
        // get currentUserId using auth
        val itemCollection = itemDao.itemCollection
        val currentUserId = auth.currentUser !!.uid

        // creat e aquery in item Collection
        //whereEqualto for the user to access its own tasks list
        val query = itemCollection.whereEqualTo("uid",currentUserId)
            .orderBy("text", com.google.firebase.firestore.Query.Direction.ASCENDING ) //for alphabetical order

        //create a firestoreRecyclerViewOption
        val recyclerViewOption = FirestoreRecyclerOptions.Builder<Item>()
            .setQuery(query,Item::class.java).build()

        //Adding adaptor now
        adaptor = RVadaptor(recyclerViewOption)
        recyclerView.adapter = adaptor

        //ItemTouchHelper for 'swipe to delete'
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                adaptor.deleteItem(position)
            }
        }).attachToRecyclerView(recyclerView)

    }


    override fun onStart() {
        super.onStart()
        adaptor.startListening()
    }

    override fun onStop() {
        super.onStop()
        adaptor.stopListening()
    }


}
