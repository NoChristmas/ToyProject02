package com.example.tp02.board.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp02.board.dto.BoardDTO
import com.example.tp02.databinding.BoarditemRecyclerviewBinding

class BoardAdapter(private val boardList : List<BoardDTO>) : RecyclerView.Adapter<BoardAdapter.BoardDTOViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardDTOViewHolder {
        val binding = BoarditemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardDTOViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardDTOViewHolder, position: Int) {
        holder.bind(boardList[position])
    }

    override fun getItemCount(): Int = boardList.size


    inner class BoardDTOViewHolder(private val binding: BoarditemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoardDTO) {
            binding.bdNo.text = item.bd_no.toString()
            binding.bdName.text = item.bd_name
            binding.urName.text = item.ur_name
            binding.bdRegDate.text = item.bd_reg_date
            binding.bdHit.text = item.bd_hit.toString()
        }
    }

}