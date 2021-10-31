package com.sheriff.githubissues.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sheriff.githubissues.R
import com.sheriff.githubissues.adapter.GithubIsssueAdapter
import com.sheriff.githubissues.model.network.RetrofitInstance
import com.sheriff.githubissues.model.response.GithubIssuesResponse
import com.sheriff.githubissues.viewmodel.GithubIssuesViewModel
import kotlinx.android.synthetic.main.activity_github_issues.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class GithubIssuesActivity : AppCompatActivity() {

    lateinit var githubIssuesViewModel: GithubIssuesViewModel

    companion object{
        val TAG = "MainActivity"
    }

    // input format (we get a value as Epoch)
    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private val outputFormat = SimpleDateFormat("MMM dd yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_issues)
        loadAPIData()
    }

    private fun loadAPIData(){
        githubIssuesViewModel = ViewModelProvider(this).get(GithubIssuesViewModel::class.java)
        githubIssuesViewModel.getGithubIssuesListObserver().observe(this, Observer<List<GithubIssuesResponse>>{
            when{
                it != null -> {
                    initAdapter(it)
                }
                else ->{
                    Toast.makeText(this, "Error in fetching the data", Toast.LENGTH_SHORT).show()
                }
            }
        })
        githubIssuesViewModel.makeApiCall()
    }

    private fun initAdapter(response: List<GithubIssuesResponse>){
        rvGithubList.apply {
            val linearLayoutManager = LinearLayoutManager(this@GithubIssuesActivity)
            layoutManager = linearLayoutManager
            val githubIsssueAdapter = GithubIsssueAdapter(response)
            adapter = githubIsssueAdapter
        }
    }
}