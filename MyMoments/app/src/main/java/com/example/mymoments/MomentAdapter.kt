package com.example.mymoments

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MomentAdapter(private var momentList: MutableList<Moment>) : RecyclerView.Adapter<MomentAdapter.MomentViewHolder> {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MomentAdapter.MomentViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MomentAdapter.MomentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MomentViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{


        private var view: View = v
        private lateinit var moment: MomentAdapter
        init{
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

    }
}