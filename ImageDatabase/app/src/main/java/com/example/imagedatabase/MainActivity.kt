package com.example.imagedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagedatabase.database.MyDbHandler
import com.example.imagedatabase.model.Contact

class MainActivity : AppCompatActivity() {

    var  img = ImageStorageManager()
    private lateinit var recyclerView : RecyclerView
    private lateinit var rvAdapter : RVAdapter
    private lateinit var contactArrayList : ArrayList<Contact>
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.Recview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var db = MyDbHandler(this)

        var harry = Contact()
        harry.name = "HARRY"
        harry.branch = "ITI"

//        db.addContact(harry)
//        db.addContact(harry)
        contactArrayList = ArrayList()

        var contactList : List<Contact> = db.AllContacts()

        for(con : Contact in contactList){
            contactArrayList.add(con)
        }
        //USing recyclerview

        rvAdapter = RVAdapter(this, contactArrayList)
        recyclerView.adapter = rvAdapter
    }
}