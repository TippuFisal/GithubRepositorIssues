package com.sheriff.githubissues.model.network

import com.sheriff.githubissues.model.response.GithubIssuesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/repos/{owner}/{repositoryName}/issues")
    fun getGithubIssues(
        @Path("owner") owner: String,
        @Path("repositoryName") repositoryName: String
    ): Observable<List<GithubIssuesResponse>>

}
