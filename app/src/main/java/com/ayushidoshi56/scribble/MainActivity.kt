package com.ayushidoshi56.scribble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var postReference: DatabaseReference
    private lateinit var childEventListener: ChildEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val paintView = PaintView(this)
        setContentView(paintView)


        database= Firebase.database.reference
        postReference = database.child("data")

        childEventListener = object : ChildEventListener{

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val info = snapshot.getValue<Information>()
                Log.i("DOWNLOADORNOT",info!!.type.toString()+" "+info.pointX.toString()+" "+info.pointY+toString())
                if(info?.type == 0){
                    paintView.start(info.pointX , info.pointY)
                }else if(info?.type == 2){
                    paintView.co(info!!.pointX , info.pointY)
                }
                else{
                    paintView.end(info!!.pointX , info.pointY)
                }
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
        postReference.addChildEventListener(childEventListener)
//        paintview.co(100f,105f)
//        paintview.co(201f,701f)
//        paintview.co(302f,603f)
//        paintview.co(604f,400f)
//        paintview.co(607f,908f)
    }
}
