package com.sheriff.githubissues.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sheriff.githubissues.model.network.RetrofitInstance
import com.sheriff.githubissues.model.network.RetrofitService
import com.sheriff.githubissues.model.response.GithubIssuesResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GithubIssuesViewModel : ViewModel() {

    var githubIssuesList = MutableLiveData<List<GithubIssuesResponse>>()

    init {
        githubIssuesList = MutableLiveData()
    }

    fun getGithubIssuesListObserver(): MutableLiveData<List<GithubIssuesResponse>>{
        return githubIssuesList
    }

    fun makeApiCall(){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retrofitInstance.getGithubIssues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getGithubIssueListObserverRx())
    }

    private fun getGithubIssueListObserverRx(): Observer<List<GithubIssuesResponse>> {
        return object : Observer<List<GithubIssuesResponse>>{
            override fun onSubscribe(d: Disposable) {
                // onSubscribe
            }

            override fun onNext(githubIssuesResponse: List<GithubIssuesResponse>) {
                githubIssuesList.postValue(githubIssuesResponse)
            }

            override fun onError(e: Throwable) {
                githubIssuesList.postValue(null)
            }

            override fun onComplete() {
                // onComplete
            }
        }
    }
}