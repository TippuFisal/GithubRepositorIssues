package com.sheriff.githubissues.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.sheriff.githubissues.R
import com.sheriff.githubissues.model.response.GithubIssuesResponse
import com.sheriff.githubissues.utility.Utils

class GithubIsssueAdapter(val data : List<GithubIssuesResponse>): RecyclerView.Adapter<GithubIsssueAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.findViewById<MaterialTextView>(R.id.tvTitle)
        val tvDate = itemView.findViewById<MaterialTextView>(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_github_issue_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = "Title : ${data[position].title}"
        holder.tvDate.text = "Created At : ${Utils.calculateDateMonthYear(data[position].created_at)}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

}