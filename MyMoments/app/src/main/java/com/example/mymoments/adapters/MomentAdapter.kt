package com.example.mymoments.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoments.data.Moment
import com.example.mymoments.R
import com.example.mymoments.activities.AddNewMoment

class MomentAdapter(private var momentList: MutableList<Moment>) : RecyclerView.Adapter<MomentAdapter.MomentViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MomentViewHolder {

        val context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val attachToParentImmediately = false

        val view = inflater.inflate(R.layout.recycler_moment_item, viewGroup, attachToParentImmediately)

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
        private lateinit var moment: Moment
        private var date : TextView
        private var title: TextView

        init
        {
            date = view.findViewById(R.id.date_recycler_item)
            title = view.findViewById(R.id.recycler_title_item)
            v.setOnClickListener(this)

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