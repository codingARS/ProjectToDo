package com.example.projecttodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.projecttodo.daos.ItemDao

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemEditText: EditText
    private lateinit var addItemButton: Button
    private lateinit var itemDao: ItemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        itemEditText = findViewById(R.id.itemEditText)
        addItemButton = findViewById(R.id.addItemButton)
        itemDao = ItemDao()

        addItemButton.setOnClickListener {
            val item = itemEditText.text.toString()
            if (item.isNotEmpty()) {
                // navigate to main activity
                    itemDao.addItem(item)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}