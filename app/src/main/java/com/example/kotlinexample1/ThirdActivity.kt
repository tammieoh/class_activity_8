package com.example.kotlinexample1

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample1.databinding.ActivityThirdBinding
import org.json.JSONObject

class ThirdActivity : AppCompatActivity(){

    // read in json file
    // creating an array list of villagers
    //create the adapter with the list of villagers
    //set it to the recycler view

    private lateinit var villagersList : ArrayList<Villager>
    private lateinit var binding : ActivityThirdBinding
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        villagersList = ArrayList<Villager>()
        recyclerView = binding.recyclerViewVillagers

        val jsonFileContent = loadJSONfromAssets("villagers.json")
        val jsonObject = JSONObject(jsonFileContent)
        val jsonArray = jsonObject.getJSONArray("villagers")
        // extract name, birthday, phrase


        // keywords : in and until
        for( i in 0 until jsonArray.length()) {
            val villagerJSONObject = jsonArray.getJSONObject(i)

            val name : String = villagerJSONObject.get("name").toString()
            val birthday : String = villagerJSONObject.get("birthday").toString()
            val phrase : String = villagerJSONObject.get("phrase").toString()
            val houseURL : String = villagerJSONObject.get("house").toString()
            val villagerURL : String = villagerJSONObject.get("villager").toString()

            val villagerObject : Villager = Villager(name, birthday, phrase, houseURL, villagerURL)

            villagersList.add(villagerObject)

        }
        Log.d("villagers", villagersList.toString())

        recyclerView.adapter = VillagerAdapter(villagersList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }

    private fun loadJSONfromAssets(filename:String):String{
        return applicationContext.assets.open(filename).bufferedReader().use {it.readText()}
    }
}