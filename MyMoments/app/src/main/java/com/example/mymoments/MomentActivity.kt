package com.example.mymoments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_moment.*


class MomentActivity : AppCompatActivity() {

    private var momentList : ArrayList<Moment> = ArrayList() // list represeting all moments
    private lateinit var linearLayoutManager: LinearLayoutManager // our listview Manager
    private lateinit var adapter : MomentAdapter  //adapter for each moment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moment)

        momentList.add(Moment("2019","first Moment","My first moment"))
        momentList.add(Moment("2020","second Moment","My second moment"))

        linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager
        adapter = MomentAdapter(momentList)

        recycler_view.adapter = adapter
    }
}