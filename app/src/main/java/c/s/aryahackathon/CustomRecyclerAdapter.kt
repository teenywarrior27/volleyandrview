package c.s.aryahackathon

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.view.LayoutInflater
import com.android.volley.VolleyLog.setTag






class CustomRecyclerAdapter(context:Context, personUtils:MutableList<PersonUtils>): RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){
    private var context: Context? = null
    private var personUtils: MutableList<PersonUtils>? = null


   init {
        this.context = context
        this.personUtils = personUtils
    }


    override fun onBindViewHolder(holder: CustomRecyclerAdapter.ViewHolder?, position: Int) {
        holder!!.itemView.tag = personUtils!!.get(position)

        val pu = personUtils!![position]

        holder.pName!!.text = pu.personFirstName + " " + pu.personLastName
        holder.pJobProfile!!.text = pu.jobProfile
    }

    override fun getItemCount(): Int {
        Log.e("adapter",personUtils.toString())
        return personUtils!!.size

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent!!.getContext()).inflate(R.layout.single_list_item, parent, false)
        return ViewHolder(v)
    }
    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var pName: TextView
        var pJobProfile: TextView
        init{
            pName = itemView.findViewById<View>(R.id.pNametxt) as TextView
            pJobProfile = itemView.findViewById<View>(R.id.pJobProfiletxt) as TextView
            itemView.setOnClickListener(){
                val cpu = itemView.getTag() as PersonUtils
                Toast.makeText(itemView.getContext(), cpu.getFirstName() + " " + cpu.getLastName() + " is " + cpu.getProfile(), Toast.LENGTH_SHORT).show()

            }
        }

    }

}