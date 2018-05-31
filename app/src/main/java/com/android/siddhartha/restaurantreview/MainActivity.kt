package com.android.siddhartha.restaurantreview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getResult(view: View) {

        if(review.text.toString() != "") {



            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
            val url = "https://review-rest-api01101998.herokuapp.com/?review=" + review.text
            val loginRequest = object: JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
                try {

                    val prediction = response.getString("prediction")

                    if(prediction == "1") {
                        Toast.makeText(this, "Thanks for your postive review!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Hope you like the food next time!", Toast.LENGTH_LONG).show()
                    }

                } catch (e: JSONException) {
                    Log.d("JSON", "EXC: " + e.localizedMessage)
                }
            }, Response.ErrorListener { error ->
                Log.d("ERROR", "Could not send mail: $error")
            }) {

                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }
            }
            Volley.newRequestQueue(this).add(loginRequest)

        } else {
            Toast.makeText(this, "Complete the form!", Toast.LENGTH_SHORT).show()
        }

    }
}
