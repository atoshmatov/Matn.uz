package uz.uicgroup.presentation.screens.edit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.uicgroup.databinding.DictionaryItemBinding
import androidx.recyclerview.widget.ListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.utils.extension.inVisible
import uz.uicgroup.utils.extension.paintResultSearch
import uz.uicgroup.utils.extension.visible

class DictionaryAdapter(private val context: Context, private val scope: CoroutineScope) :
    ListAdapter<DictionaryData, DictionaryAdapter.ViewHolder>(DictionaryCallBack) {
    var query: String? = null
    var click = false
    private var clicker = false
    private var onItemClickListener: ((Int) -> Unit)? = null

    object DictionaryCallBack : DiffUtil.ItemCallback<DictionaryData>() {
        override fun areItemsTheSame(oldItem: DictionaryData, newItem: DictionaryData): Boolean =
            false

        override fun areContentsTheSame(oldItem: DictionaryData, newItem: DictionaryData): Boolean =
            false

    }

    inner class ViewHolder(private val binding: DictionaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (!clicker) {
                    clicker = true
                    onItemClickListener!!.invoke(getItem(absoluteAdapterPosition).id)
                    scope.launch {
                        delay(500)
                        clicker = false
                        cancel()
                    }
                }
            }
        }

        fun bind(): DictionaryData = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                if (click) {
                    dictionaryText.text = latin
                    historyImage.visible()
                } else {
                    historyImage.inVisible()
                    dictionaryText.text =
                        latin.let { query?.let { it1 -> latin.paintResultSearch(it1, context) } }
                }
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

    fun setOnItemClickListener(block: (Int) -> Unit) {
        onItemClickListener = block
    }
    //83853
}