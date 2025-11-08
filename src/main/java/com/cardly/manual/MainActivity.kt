package com.cardly.manual

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cardly.manual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CardAdapter
    private var cards = mutableListOf<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.title = "Cardly"

        cards = Storage.loadCards(this)
        adapter = CardAdapter(cards)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            val i = Intent(this, AddCardActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        cards.clear()
        cards.addAll(Storage.loadCards(this))
        adapter.notifyDataSetChanged()
    }
}
