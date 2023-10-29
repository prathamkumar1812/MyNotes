package com.example.mynotes.activities

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.R
import com.example.mynotes.ViewModle.NotesViewModel
import com.example.mynotes.ViewModle.NotesViewModleFactory
import com.example.mynotes.modles.Notes
import com.example.mynotes.modles.NotesDatabase
import com.example.mynotes.modles.NotesRepository

class EditNotes : AppCompatActivity() {
    lateinit var notesViewModel:NotesViewModel
    lateinit var title:EditText
    lateinit var note:EditText
    lateinit var colorpicker:LinearLayout
    lateinit var backgoudn:LinearLayout
    var id:Int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notes)
        colorpicker=findViewById(R.id.colorpicker)
         title=findViewById<EditText>(R.id.Title)
        backgoudn=findViewById(R.id.backroigh)
         note=findViewById<EditText>(R.id.Note)
        val dao= NotesDatabase.getDatabase(applicationContext).notesDao()
        val notesRepository= NotesRepository(dao)
         id =intent.getIntExtra("id",0)
         title.setText(intent.getStringExtra("Title"))
        note.setText(intent.getStringExtra("text"))
        notesViewModel=
            ViewModelProvider(this, NotesViewModleFactory(notesRepository)).get(NotesViewModel::class.java)

        notesViewModel.DeleteNotes(Notes(id,title.text.toString(),note.text.toString()))
    }

    override fun onPause() {
        if(checktext()){
            notesViewModel.InsetNotes(Notes(id,title.text.toString(),note.text.toString()));
        }

        super.onPause()
    }
    fun checktext():Boolean{
        if(note.text.toString().trim().isEmpty()){
            return false
        }
        return true
    }

    fun color(view: View) {
        if(colorpicker.visibility==View.VISIBLE){
            colorpicker.visibility=View.GONE
        }
        else{
            colorpicker.visibility=View.VISIBLE
        }
    }
    fun setbackgroundcolor(){

    }

    fun colors(view: View) {
        backgoudn.background=view.background

    }

}