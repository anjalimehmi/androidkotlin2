package com.o7.androidkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore
import com.o7.androidkotlin.databinding.ActivityFirestoreBinding

class Firestore : AppCompatActivity(), CategoriesListAdapter.onClick {
    lateinit var binding: ActivityFirestoreBinding
    var ItemLayout = arrayListOf<CategoriesListModel>()
    lateinit var categoriesListAdapter: CategoriesListAdapter

    val db = Firebase.firestore
    var collectionName = "Users"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db.collection(collectionName).addSnapshotListener { snapshots, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            for (snapshot in snapshots!!.documentChanges) {
                val userModel = convertObject(snapshot.document)

                when (snapshot.type) {
                    DocumentChange.Type.ADDED -> {
                        userModel?.let { ItemLayout.add(it) }
                        Log.e("", "userModelList ${ItemLayout.size}")
                        Log.e("", "userModelList ${ItemLayout}")
                    }

                    DocumentChange.Type.MODIFIED -> {
                        userModel?.let {
                            var index = getIndex(userModel)
                            if (index > -1)
                                ItemLayout.set(index, it)
                        }
                    }

                    DocumentChange.Type.REMOVED -> {
                        userModel?.let {
                            var index = getIndex(userModel)
                            if (index > -1)
                                ItemLayout.removeAt(index)
                        }
                    }
                }
            }
            categoriesListAdapter.notifyDataSetChanged()
        }
        categoriesListAdapter = CategoriesListAdapter(this, ItemLayout, this)
        binding.fsrecycler.layoutManager = LinearLayoutManager(this)
        binding.fsrecycler.adapter = categoriesListAdapter

        binding.flbtn2.setOnClickListener {
            try {
                db.collection(collectionName).add(CategoriesListModel(categoryName = "New Category"))
                    .addOnSuccessListener {
                        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()
                    }
            } catch (e: Exception) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun convertObject(snapshot: QueryDocumentSnapshot): CategoriesListModel? {
        val categoriesModel: CategoriesListModel? =
            snapshot.toObject(CategoriesListModel::class.java)
        categoriesModel?.categoryId = snapshot.id ?: ""
        return categoriesModel
    }

    fun getIndex(categoriesModel: CategoriesListModel): Int {
        var index = -1
        index = ItemLayout.indexOfFirst { element ->
            element.categoryId?.equals(categoriesModel.categoryId) == true
        }
        return index
    }

    override fun delete(position: Int) {
        ItemLayout[position].categoryId?.let {
            db.collection(collectionName).document(it).delete()
                .addOnSuccessListener {
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()

                }
        }
    }

    override fun update(position: Int) {
        ItemLayout[position].categoryId?.let {
            db.collection(collectionName).document(it)
                .set(CategoriesListModel(categoryName = "Document Updated"))
                .addOnSuccessListener {
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Update Failed ", Toast.LENGTH_SHORT).show()

                }
        }

    }
}