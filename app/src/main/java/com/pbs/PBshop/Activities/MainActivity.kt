package com.pbs.PBshop.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pbs.PBshop.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        login.setOnClickListener {
            /*if(user.text.isNotEmpty() || password.text.isNotEmpty()){
                val user1:String =user.text.toString()
                val  pass:String=password.text.toString()
                if(existe(user1,pass)){*/
                    val intent = Intent(this, com.pbs.PBshop.Activities.Menu::class.java)
                    startActivity(intent)
            /*    }
            }else {
                Toast.makeText(this, "Los datos no pueden estar vácios", Toast.LENGTH_SHORT).show()

            }*/
        }
    }

    fun existe(user:String,password:String):Boolean{

        return true
    }




}
