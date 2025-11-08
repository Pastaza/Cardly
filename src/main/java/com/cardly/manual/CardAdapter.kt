package com.cardly.manual

import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cardly.manual.databinding.ItemCardBinding
import java.util.concurrent.TimeUnit

class CardAdapter(private val items: MutableList<Card>) : RecyclerView.Adapter<CardAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = items[position]
        holder.binding.tvBrand.text = c.brand

        val days = if (c.expiryMillis > 0) {
            val diff = c.expiryMillis - System.currentTimeMillis()
            TimeUnit.MILLISECONDS.toDays(diff)
        } else -1

        val color = when {
            days < 0 -> Color.RED
            days <= 3 -> Color.parseColor("#FFA500") // orange
            else -> Color.GRAY
        }

        holder.binding.tvExpiry.setTextColor(color)
        holder.binding.tvExpiry.text = if (days >= 0) "Expires in $days days" else "No expiry"
        holder.binding.tvNotes.text = c.notes

        // 🗑️ Delete button functionality
        holder.binding.btnDelete.setOnClickListener {
            val context = holder.itemView.context

            // Confirmation dialog
            AlertDialog.Builder(context)
                .setTitle("Delete Card")
                .setMessage("Are you sure you want to delete ${c.brand}?")
                .setPositiveButton("Delete") { _, _ ->
                    items.removeAt(position)
                    Storage.saveCards(context, items)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun getItemCount(): Int = items.size

    class VH(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)
}

