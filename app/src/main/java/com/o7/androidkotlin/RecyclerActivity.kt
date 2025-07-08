package com.o7.androidkotlin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.o7.androidkotlin.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity(), recycler_adapter.OnClick {
    lateinit var binding: ActivityRecyclerBinding
    lateinit var recyclerAdapter: recycler_adapter
    var Noteslist= arrayListOf<rc_dataclass>()

    val db = Firebase.firestore
    var collectionName="Users"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Noteslist.add(rc_dataclass(title = "My title", description = "My description"))
        Noteslist.add(rc_dataclass(title = "My name", description = "My description"))
        Noteslist.add(rc_dataclass(title = "description", description = "My description"))
        recyclerAdapter= recycler_adapter(Noteslist,this)
        binding.rcview.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rcview.adapter=recyclerAdapter
        recyclerAdapter.notifyDataSetChanged()

        
        binding.flbtn.setOnClickListener {
            var dialog= Dialog(this)

            dialog.setContentView(R.layout.dialogueview)

            var mytext=dialog.findViewById<EditText>(R.id.ip1)
            var mytext1=dialog.findViewById<EditText>(R.id.ip2)
            var mytext2=dialog.findViewById<EditText>(R.id.ip3)

//            dialog.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//            )

            dialog.show()
            var btnCancel = dialog.findViewById<Button>(R.id.ipbtn2)
            var btnSave = dialog.findViewById<Button>(R.id.ipbtn1)

            btnCancel.setOnClickListener{

                Noteslist.add(rc_dataclass(title = mytext.text.toString()))
                Toast.makeText(this@RecyclerActivity, "${mytext.text}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            btnSave.setOnClickListener{

                Noteslist.add(rc_dataclass(title = mytext.text.toString()))
                Toast.makeText(this@RecyclerActivity, "${mytext.text}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        }
    }

    override fun update(position: Int) {
        Toast.makeText(this,"Update clicked", Toast.LENGTH_SHORT).show()
    }

    override fun delete(position: Int) {
        Noteslist.removeAt(position)
        Toast.makeText(this,"Delete clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int){
        Toast.makeText(this@RecyclerActivity,"...", Toast.LENGTH_SHORT).show()
        var intent= Intent(this, navigate::class.java )
        startActivity(intent)
    }


}