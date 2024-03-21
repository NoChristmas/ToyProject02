package com.example.tp02.board.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp02.board.dto.BoardDTO
import com.example.tp02.databinding.BoarditemRecyclerviewBinding

class BoardAdapter : ListAdapter<BoardDTO, BoardAdapter.Holder>(BoardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = BoarditemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class Holder(private val binding: BoarditemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoardDTO) {
            binding.apply {
                bdNo.text = item.bd_no.toString()
                bdName.text = item.bd_name
                urName.text = item.ur_name
                bdRegDate.text = item.bd_reg_date.toString()
                bdHit.text = item.bd_hit.toString()
            }
        }
    }

    private class BoardDiffCallback : DiffUtil.ItemCallback<BoardDTO>() {
        override fun areItemsTheSame(oldItem: BoardDTO, newItem: BoardDTO): Boolean {
            return oldItem.bd_no == newItem.bd_no
        }

        override fun areContentsTheSame(oldItem: BoardDTO, newItem: BoardDTO): Boolean {
            return oldItem == newItem
        }
    }
}