package com.example.mynotes.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.activities.EditNotes
import com.example.mynotes.R
import com.example.mynotes.ViewModle.NotesViewModel
import com.example.mynotes.modles.Notes
import com.example.mynotes.modles.NotesDatabase
import com.example.mynotes.modles.NotesRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NotesAdapter(val context: Context):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    lateinit var NotesViewModel:NotesViewModel
    var list= listOf<Notes>()

    inner class NotesViewHolder(Itemlist:View):RecyclerView.ViewHolder(Itemlist){
        val text=Itemlist.findViewById<TextView>(R.id.textNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       val view=LayoutInflater.from(context).inflate(R.layout.list_view,parent,false)
        return NotesViewHolder(view) }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note=list[position]
        holder.text.text=note.text

        holder.text.setOnClickListener {
            val i= Intent(context, EditNotes::class.java)
            i.putExtra("Title",note.title)
            i.putExtra("text",note.text)
            i.putExtra("id",note.id)
            val options =
            //ActivityOptions.makeCustomAnimation(context, com.airbnb.lottie.R.anim.abc_slide_in_top, com.airbnb.lottie.R.anim.abc_slide_in_top)
            context.startActivity(i)
        }
        holder.text.setOnLongClickListener {
            val dao= NotesDatabase.getDatabase(context).notesDao()
            val notesRepository= NotesRepository(dao)
            val notessss=NotesViewModel(notesRepository)
            val alart=AlertDialog.Builder(context)
            alart.setTitle("Delete")
            alart.setIcon(R.drawable.baseline_delete_24)
            alart.setMessage("Are you sure want to delete this")
            alart.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                notessss.DeleteNotes(note)
                notifyDataSetChanged()
            })
            alart.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(context,"No operation done",Toast.LENGTH_SHORT).show()
            })
            alart.show()

            true
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setlist(list:List<Notes>){
       this.list=list
        notifyDataSetChanged()
    }

}


