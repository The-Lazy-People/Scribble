package com.ayushidoshi56.scribble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var postReference: DatabaseReference
    private lateinit var childEventListener: ChildEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paintview=PaintView(this)
        setContentView(paintview)
        database= FirebaseDatabase.getInstance().reference
        postReference = database.child("data")
        childEventListener = object : ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {
                // not needed
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // not needed
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // not needed
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // not needed
            }

        }
//        paintview.co(100f,105f)
//        paintview.co(201f,701f)
//        paintview.co(302f,603f)
//        paintview.co(604f,400f)
//        paintview.co(607f,908f)
    }
}