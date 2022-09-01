package com.example.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.Api.ApiConfig
import com.example.quotes.Api.MainAdapter
import com.example.quotes.Api.Quote
import com.example.quotes.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    private lateinit var mainAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
       // getDataFromApi()
    }

    override fun onStart() {
        super.onStart()
            getDataFromApi()
    }

    private fun getDataFromApi() {
        Log.d("MainActivity","getDataFromApi")
        ApiConfig.getService().getQuote().enqueue(object : Callback<List<Quote>> {
            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                if(!response.isSuccessful){
                    Log.d("MainActivity",response.message() + " response code = " +response.code())
                }
                if(response.isSuccessful){
                    Log.d("MainActivity",response.body().toString() + " response code = " +response.code())
                    mainAdapter = MainAdapter(this@MainActivity,response.body()!!)
                    mainAdapter.notifyDataSetChanged()

                    binding!!.recyclerView.adapter = mainAdapter
                    binding!!.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding!!.recyclerView.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                Log.d("MainActivity", t.message.toString())

            }


        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}



