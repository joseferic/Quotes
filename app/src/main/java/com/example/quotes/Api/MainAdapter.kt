package com.example.quotes.Api

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.databinding.AdapterQuoteBinding
import java.util.*

class MainAdapter(var context: Context, var quoteList: List<Quote>):RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    inner class MainViewHolder(val itemBinding: AdapterQuoteBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(quote: Quote){
            itemBinding.tvText.text = quote?.text
            itemBinding.tvAuthor.text = quote?.author
            itemBinding.container.setOnClickListener(View.OnClickListener {
                copyTextToClipboard(quote?.text +"\n\n- "+ quote?.author)
            })
        }

    }

    private fun copyTextToClipboard(textToCopy : String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MainViewHolder { return MainViewHolder(AdapterQuoteBinding
        .inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val quote = quoteList?.get(position)
        holder.bindItem(quote)
    }

    override fun getItemCount(): Int {
        return quoteList?.size!!
    }



}