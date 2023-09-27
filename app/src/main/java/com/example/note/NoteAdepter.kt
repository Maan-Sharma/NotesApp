package com.example.note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R

class NoteAdepter(val context:Context,val listener:INoteAdepter):RecyclerView.Adapter<NoteAdepter.NoteViewHolder>() {

    val allNote=ArrayList<Note>()

    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder=NoteViewHolder( LayoutInflater.from(context).inflate(R.layout.item,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNote[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNote[position]
        holder.textView.text=currentNote.text
    }

    fun updateList(newList:List<Note>){
        allNote.clear()
        allNote.addAll(newList)

        notifyDataSetChanged()
    }

}
interface INoteAdepter{
    fun onItemClicked(note: Note)
}