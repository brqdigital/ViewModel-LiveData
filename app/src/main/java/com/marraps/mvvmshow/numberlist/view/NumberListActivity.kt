package com.marraps.mvvmshow.numberlist.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marraps.mvvmshow.R
import com.marraps.mvvmshow.numberlist.model.NumbersResponse
import com.marraps.mvvmshow.numberlist.model.NumbersServiceListener
import com.marraps.mvvmshow.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumberListActivity : AppCompatActivity(), NumbersServiceListener {

    private val numberList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_numbers_list)
    }

    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.pb_list_loading)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_list)

        setList()

        showLoading()
        getNumbers(this)
    }

    private fun setList() {
        numberList.apply {
            layoutManager = LinearLayoutManager(this@NumberListActivity)
            adapter = NumbersListAdapter(emptyList())
            setHasFixedSize(true)
        }
    }

    private fun showLoading() {
        numberList.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        numberList.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun getNumbers(listener: NumbersServiceListener) {

        val call = RetrofitService.getService().getNumbers()
        call.enqueue(object : Callback<NumbersResponse> {

            override fun onResponse(
                call: Call<NumbersResponse>,
                response: Response<NumbersResponse>
            ) {
                response.body()?.let {
                    listener.onSuccess(it)

                } ?: listener.onError(Exception())
            }

            override fun onFailure(call: Call<NumbersResponse>, t: Throwable) {
                listener.onError(t)
            }
        })
    }

    override fun onSuccess(response: NumbersResponse) {
        numberList.adapter = NumbersListAdapter(response.numbers)
        hideLoading()
    }

    override fun onError(error: Throwable) {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
        hideLoading()
    }
}
