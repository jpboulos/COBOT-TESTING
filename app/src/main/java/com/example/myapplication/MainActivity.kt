package com.example.myapplication



import android.content.ContentValues.TAG
import org.json.JSONObject
import android.os.Bundle
import android.util.Log


import android.widget.Button

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST


import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


import java.io.IOException


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.122:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        fun sendMessage(message: String) {
            val requestBody = ApiService.RequestBody(message)
            api.sendPostRequest(requestBody).enqueue(object : Callback<ApiService.ResponseData> {
                override fun onResponse(
                    call: Call<ApiService.ResponseData>,
                    response: Response<ApiService.ResponseData>
                ) {
                    val responseData = response.body()
                    Log.d("Response", responseData?.response ?: "")
                }

                override fun onFailure(call: Call<ApiService.ResponseData>, t: Throwable) {
                    Log.e("Error", t.message ?: "Unknown error")
                }
            })
        }






        val btn_connect = findViewById<Button>(R.id.Connect_button)

        btn_connect.setOnClickListener {
            sendMessage("FF")



        }
        val btn_disconnect = findViewById<Button>(R.id.Disconnect_button)

        btn_disconnect.setOnClickListener {


            Toast.makeText(this@MainActivity, "Disconnected successfully", Toast.LENGTH_SHORT)
                .show()
        }

    }


}










