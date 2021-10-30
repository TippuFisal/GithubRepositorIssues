package com.sheriff.githubissues.model.network

import com.sheriff.githubissues.model.response.GithubIssuesResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/repos/octocat/hello-world/issues")
    fun getGithubIssues(): Observable<List<GithubIssuesResponse>>

}
