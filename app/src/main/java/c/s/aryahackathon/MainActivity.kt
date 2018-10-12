package c.s.aryahackathon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.RequestQueue
import android.support.v7.widget.RecyclerView
import com.android.volley.toolbox.Volley
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.*
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var mAdapter: RecyclerView.Adapter<*>? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var personUtilsList: MutableList<PersonUtils>? = null

    var rq: RequestQueue? = null
    var request_url = "https://reqres.in/api/unknown"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rq = Volley.newRequestQueue(this)
        recyclerView = findViewById<RecyclerView>(R.id.recycleViewContainer)
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)

        recyclerView!!.setLayoutManager(layoutManager)
        personUtilsList= mutableListOf<PersonUtils>()
        sendRequest()



    }
    fun sendRequest(){
        var jsonArrayRequest =  JsonObjectRequest( request_url, null,  object : Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                val arr=response!!.getJSONArray("data")
                for(i in 0..arr.length()-1){
                    val personUtils = PersonUtils()
                    try{
                        val jsonObject = arr.getJSONObject(i)
                        personUtils.setname(jsonObject.getString("name"));
                        personUtils.setlasname(jsonObject.getString("year"));
                        personUtils.setjobprofile(jsonObject.getString("pantone_value"));
                    }catch(e:JSONException){
                        e.printStackTrace();
                    }
                    personUtilsList!!.add(personUtils)
                }
                if(personUtilsList!=null){
                    Log.e("list ",personUtilsList.toString())
                mAdapter = CustomRecyclerAdapter(this@MainActivity, personUtilsList!!)

                recyclerView!!.setAdapter(mAdapter)}
            }



        },object:Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Log.e("Volley Error: ", error.toString())
            }

        })
        rq!!.add(jsonArrayRequest)

    }
}
