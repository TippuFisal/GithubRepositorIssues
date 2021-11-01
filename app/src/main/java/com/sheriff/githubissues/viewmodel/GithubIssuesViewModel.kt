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

    private val githubIssuesList:MutableLiveData<List<GithubIssuesResponse>> = MutableLiveData<List<GithubIssuesResponse>>() // Live Data

    /**
     * getGithubIssuesListObserver
     */
    fun getGithubIssuesListObserver(): MutableLiveData<List<GithubIssuesResponse>> {
        return githubIssuesList
    }

    /**
     * makeApiCall
     */
    fun makeApiCall() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retrofitInstance.getGithubIssues("square","okhttp")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getGithubIssueListObserverRx())
    }

    /**
     * getGithubIssueListObserverRx
     */
    private fun getGithubIssueListObserverRx(): Observer<List<GithubIssuesResponse>> {
        return object : Observer<List<GithubIssuesResponse>> {
            override fun onSubscribe(d: Disposable) {
                // onSubscribe 1st
            }

            override fun onNext(githubIssuesResponse: List<GithubIssuesResponse>) {
                githubIssuesList.postValue(githubIssuesResponse) // we will get API response
            }

            override fun onError(e: Throwable) {
                githubIssuesList.postValue(null) // onError
            }

            override fun onComplete() {
                // onComplete 4th
            }
        }
    }
}