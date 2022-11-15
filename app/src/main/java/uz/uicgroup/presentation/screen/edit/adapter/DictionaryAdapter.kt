package uz.uicgroup.presentation.screen.edit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.uicgroup.databinding.DictionaryItemBinding
import uz.uicgroup.domain.model.WordData
import androidx.recyclerview.widget.ListAdapter
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.utils.extension.paintResultSearch

class DictionaryAdapter(private val context: Context) :
    ListAdapter<DictionaryData, DictionaryAdapter.ViewHolder>(DictionaryCallBack) {
    var query: String? = null

    object DictionaryCallBack : DiffUtil.ItemCallback<DictionaryData>() {
        override fun areItemsTheSame(oldItem: DictionaryData, newItem: DictionaryData): Boolean =
            false

        override fun areContentsTheSame(oldItem: DictionaryData, newItem: DictionaryData): Boolean =
            false

    }

    inner class ViewHolder(private val binding: DictionaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

        }

        fun bind(): DictionaryData = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                dictionaryText.text =
                    latin.let { query?.let { it1 -> latin.paintResultSearch(it1, context) } }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DictionaryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
    //83853
}