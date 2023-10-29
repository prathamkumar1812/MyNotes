package com.example.mynotes.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotes.Adapter.NotesAdapter
import com.example.mynotes.R
import com.example.mynotes.ViewModle.NotesViewModel
import com.example.mynotes.ViewModle.NotesViewModleFactory
import com.example.mynotes.modles.NotesDatabase
import com.example.mynotes.modles.NotesRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Notelist : AppCompatActivity() ,SearchView.OnQueryTextListener{
    lateinit var listview:RecyclerView
    lateinit var addNotes:FloatingActionButton
    lateinit var notesViewModel: NotesViewModel
    lateinit var seach:EditText
    lateinit var SearchButton:ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notelist)


        listview=findViewById(R.id.listRecyclview)

        addNotes=findViewById(R.id.addntoes)
      val dao=NotesDatabase.getDatabase(applicationContext).notesDao()
       val notesRepository=NotesRepository(dao)


        notesViewModel=ViewModelProvider(this,NotesViewModleFactory(notesRepository)).get(NotesViewModel::class.java)


        notesViewModel.getNotes().observe(this, Observer {
            val adapter=NotesAdapter(this)
            adapter.setlist(it)
            listview.adapter=adapter
            listview.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter.notifyDataSetChanged()
        })



    addNotes.setOnClickListener {
        startActivity(Intent(this, EditNotes::class.java))
    }





    }
    // we have override this function so that we can add our menu here
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
       // searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        // after this you need implement your setOnQueryTextListener in main activity for pass 'this'
        //for implement just go above and add it to last like this
        //  class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
         return  true
    }

    override fun onQueryTextChange(query: String?): Boolean {

        if(query != null){
            searchDatabase(query)
        }
        return true
    }
    private fun searchDatabase(query: String) {
        // %" "% because our costume sql query will require that
        val searchQuery = "%$query%"

        notesViewModel.searchlist(searchQuery).observe(this, Observer {
            val adapter=NotesAdapter(this,)

            listview.adapter=adapter
            adapter.setlist(it)
            listview.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter.notifyDataSetChanged()
        })
    }


}