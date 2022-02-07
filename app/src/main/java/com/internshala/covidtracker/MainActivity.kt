package com.internshala.covidtracker

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var recycler:RecyclerView
    lateinit var adapter:Adapter
    lateinit var caseList:ArrayList<Modal>
    private lateinit var temporaryList:ArrayList<Modal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler=findViewById(R.id.recycler)
        caseList= arrayListOf<Modal>()
        temporaryList= arrayListOf<Modal>()
        val url="https://data.covid19india.org/state_district_wise.json"
        val queue= Volley.newRequestQueue(this)
        val request= JsonObjectRequest(Request.Method.GET,url,null,{
        val key=it.keys()
        val iterator=key.iterator()
        while (iterator.hasNext()){
           val state=iterator.next()
           val stateObject=it.getJSONObject(state)
           val districtObject=stateObject.getJSONObject("districtData")
           val alldistrictName=districtObject.keys()
           val iterator2=alldistrictName.iterator()
           while (iterator2.hasNext()){
             val districtName=iterator2.next()
             val districtData=districtObject.getJSONObject(districtName)
             val active=districtData.getInt("active").toString()
             val recovered=districtData.getInt("recovered").toString()
             val confirmed=districtData.getInt("confirmed").toString()
             val deceased=districtData.getInt("deceased").toString()
             val deltaObject=districtData.getJSONObject("delta")
             val deltaConfirmed=deltaObject.getInt("confirmed").toString()
             val deltadeceased=deltaObject.getInt("deceased").toString()
             val deltaRecovered=deltaObject.getInt("recovered").toString()

             val modal=Modal(districtName,active,confirmed,recovered,deceased,deltaConfirmed,deltaRecovered,deltadeceased)
             caseList.add(modal)

           }
            temporaryList.addAll(caseList)
        }

            adapter= Adapter(temporaryList,this)
            recycler.adapter=adapter
            recycler.layoutManager= LinearLayoutManager(this)






        },
            {
                Toast.makeText(this,"Some Unexpected Error Occured",Toast.LENGTH_SHORT).show()
            })

        queue.add(request)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu,menu)
        val item=menu?.findItem(R.id.searchIcon)
        val searchView=item?.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                temporaryList.clear()
                val searchtext=newText!!.toLowerCase(Locale.getDefault())

                if(searchtext.isNotEmpty()){
                    caseList.forEach{

                        if (it.districtName.toLowerCase(Locale.getDefault()).contains(searchtext)){
                            temporaryList.add(it)
                        }
                    }

                    recycler.adapter!!.notifyDataSetChanged()


                }

                else{
                    temporaryList.clear()
                    temporaryList.addAll(caseList)
                    recycler.adapter!!.notifyDataSetChanged()
                }



                return false
            }

        })

        return super.onCreateOptionsMenu(menu)

    }
}