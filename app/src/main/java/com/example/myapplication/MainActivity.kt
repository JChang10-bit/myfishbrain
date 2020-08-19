package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_first.*

interface Communicator {
    fun passDataCom(catchInfoInstance: CatchInfo)
}

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var catchInfoAdapter: CatchInfoRecyclerAdapter
    private lateinit var mainCatchRepo: MutableList<CatchInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun passDataCom(catchInfoInstance: CatchInfo) {
        mainCatchRepo.add(catchInfoInstance)
        saveMainCatchInfo()
    }

    fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            catchInfoAdapter = CatchInfoRecyclerAdapter()
            adapter = catchInfoAdapter
            catchInfoAdapter.submitList(mainCatchRepo)
        }
    }

    private fun saveMainCatchInfo() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(mainCatchRepo)
        editor.putString("task list", json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", "")

        if (json != null) {
            if (json.isBlank()) {
                mainCatchRepo = ArrayList()
            } else {
                val type = object: TypeToken<MutableList<CatchInfo>>() {
                }.type
                mainCatchRepo = gson.fromJson(json, type)
            }
        }
    }

    fun clearData() {
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
    }
}