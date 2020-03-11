package com.marraps.mvvmshow.numberlist.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marraps.mvvmshow.R
import com.marraps.mvvmshow.numberlist.viewmodel.NumberListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NumberListActivity : AppCompatActivity() {

    private val numberList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_numbers_list)
    }

    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.pb_list_loading)
    }

    private val viewModel: NumberListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_list)

        setList()
        setObservers()

        viewModel.getNumbers()
    }

    private fun setList() {
        numberList.apply {
            layoutManager = LinearLayoutManager(this@NumberListActivity)
            adapter = NumbersListAdapter(emptyList())
            setHasFixedSize(true)
        }
    }

    private fun setObservers() {
        viewModel.command.observe(this, Observer {
            when (it) {
                is NumberListViewModel.Command.ShowLoading -> showLoading()
                is NumberListViewModel.Command.HideLoading -> hideLoading()
            }
        })

        viewModel.numberList.observe(this, Observer {
            numberList.adapter = NumbersListAdapter(it)
        })

        viewModel.numberError.observe(this, Observer {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading() {
        numberList.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        numberList.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}
