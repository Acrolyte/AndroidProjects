package com.example.dbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dbdemo.data.MyDbHandler
import com.example.dbdemo.model.Contact

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val some = this
        val db = MyDbHandler(some)

        //Contact1
        val someone: Contact = Contact()
        someone.phoneNumber = "909090"
        someone.name = "Harry"

//        db.addContact(someone)

        //Contact2
        val sometwo: Contact = Contact()
        sometwo.phoneNumber = "90900"
        sometwo.name = "Manny"

//        db.addContact(sometwo)

        //Contact3
        val somethri: Contact = Contact()
        somethri.phoneNumber = "903290"
        somethri.name = "Fearry"

//       db.addContact(somethri)

     //  Log.d("dbdb", "IDs: " + someone.name + someone.phoneNumber + sometwo.id + somethri.id)

        //Getting Contacts
        val allContact  = db.AllContacts()
        for (con  in allContact) {
         //   db.deleteContact(con)
        }

        for (con  in allContact) {
            Log.d("dbdb", "Name: " + con.name + "\n" + "Id: " + con.id + "\n" + "Phone no: " + con.phoneNumber )
        }

        Log.d("dbdb", "Bro you have "+db.count+" contacts in your database.");
    }
}