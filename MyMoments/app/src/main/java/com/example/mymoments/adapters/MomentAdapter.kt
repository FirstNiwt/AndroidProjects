package com.example.mymoments.adapters

import android.content.Intent
import android.text.TextUtils.indexOf
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoments.data.Moment
import com.example.mymoments.R
import com.example.mymoments.activities.AddNewMoment
import com.example.mymoments.data.DatabaseManager.MomentEntry.TABLE_NAME
import com.example.mymoments.data.DatabaseManager.MomentEntry._ID
import com.example.mymoments.data.MomentDBHelper
import kotlinx.android.synthetic.main.activity_add_new_moment.view.*
import kotlinx.android.synthetic.main.recycler_moment_item.view.*

class MomentAdapter(private var momentList: MutableList<Moment>) : RecyclerView.Adapter<MomentAdapter.MomentViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int,
    ): MomentViewHolder {


        val context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val attachToParentImmediately = false

        val view = inflater.inflate(R.layout.recycler_moment_item, viewGroup, attachToParentImmediately)

        val holder = MomentViewHolder(view)
        view.delete_moment.setOnClickListener {

            val x = view.recycler_title_item.text

            val deleted_moment = momentList.find{it.title.equals(x)}

            val position1 = momentList.indexOf(deleted_moment)

            val mDBHelper = MomentDBHelper(view.context)

            val db = mDBHelper.writableDatabase


            val selection = "$_ID = ?"
            val selectionArgs = arrayOf("${(momentList[position1].id)}")

            db.delete(TABLE_NAME, selection, selectionArgs)

            momentList.removeAt(position1)

            notifyDataSetChanged()


        }


        return MomentViewHolder(view)
    }


    override fun onBindViewHolder(holder: MomentViewHolder, position: Int) {
        val item = momentList[position]
        holder.attachMoment(item)
    }

    override fun getItemCount(): Int {
        return momentList.size

    }


  class  MomentViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{


        private var view: View = v
        lateinit var moment: Moment
        private var date : TextView
        private var title: TextView
        val index:Int


        init
        {
            date = view.findViewById(R.id.date_recycler_item)
            title = view.findViewById(R.id.recycler_title_item)
            v.setOnClickListener(this)
            index = this.absoluteAdapterPosition

        }

        fun attachMoment(moment: Moment)
        {
            this.moment = moment

            date.text = moment.date
            title.text = moment.title
        }

        override fun onClick(v: View?) {

            val context = itemView.context
            val intent = Intent(context, AddNewMoment::class.java)
            intent.putExtra("rowID",moment.id)
            context.startActivity(intent)

        }

    }
}