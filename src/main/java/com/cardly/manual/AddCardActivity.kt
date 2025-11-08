package com.cardly.manual

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cardly.manual.databinding.ActivityAddCardBinding
import java.util.Calendar

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding
    private var expiryMillis: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Go Back button
        binding.btnBack.setOnClickListener {
            finish() // closes this activity and returns to the previous screen
        }

        // Pick expiry date
        binding.btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val cal = Calendar.getInstance()
                    cal.set(year, month, dayOfMonth, 0, 0, 0)
                    expiryMillis = cal.timeInMillis
                    binding.btnPickDate.text = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Save card
        binding.btnSave.setOnClickListener {
            val brand = binding.etBrand.text.toString().trim()
            val notes = binding.etNotes.text.toString().trim()

            if (brand.isEmpty() || expiryMillis == 0L) {
                Toast.makeText(this, "Please enter brand and expiry date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cards = Storage.loadCards(this)
            cards.add(Card(brand, expiryMillis, notes))
            Storage.saveCards(this, cards)

            Toast.makeText(this, "Card saved", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
