package com.bugratoklu.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_to_do.*
import kotlinx.android.synthetic.main.todo_row.*

class toDoActivity : AppCompatActivity() {

    lateinit var btnAdd: Button
    lateinit var btnDelete: Button
    lateinit var btnClear: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        btnAdd = findViewById(R.id.addButton)
        btnDelete = findViewById(R.id.deleteButton)
        btnClear = findViewById(R.id.clearButton)

        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        btnAdd.setOnClickListener {
            itemlist.add(editTodo.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        btnDelete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
        btnClear.setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(
                this, "Seçtiğiniz görev silindi: " + itemlist.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }
}