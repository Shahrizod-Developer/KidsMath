package uz.gita.kidsmath.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.kidsmath.data.model.Question
import uz.gita.kidsmath.databinding.ItemQuestionBinding
import uz.gita.kidsmath.presentation.utils.onClick


class QuestionAdapter : ListAdapter<Question, QuestionAdapter.ViewHolder>(itemEventCallback) {

    private var itemClickListener: ((Question, TextView) -> Unit)? = null
    var k = 0

    fun setSwitchChangedListener(block: (Question, TextView) -> Unit) {
        itemClickListener = block
    }


    inner class ViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.result.setOnClickListener {
                it.onClick {
                    itemClickListener?.invoke(getItem(absoluteAdapterPosition), binding.result)
                }
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)


            binding.numberOne.text = data.numberOne.toString()
            binding.numberTwo.text = data.numberTwo.toString()
            binding.operation.text = """${data.operation}"""

            if (binding.result.text.toString() == data.result.toString()) {
                binding.result.visibility = View.VISIBLE
                binding.ques.visibility = View.GONE
                k++
            } else {
                binding.result.visibility = View.GONE
                binding.ques.visibility = View.VISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private val itemEventCallback = object : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question) =
        oldItem.numberOne == newItem.numberOne

    override fun areContentsTheSame(oldItem: Question, newItem: Question) =
        oldItem.numberOne == newItem.numberOne
                && oldItem.numberTwo == newItem.numberTwo
                && oldItem.result == newItem.result
                && oldItem.operation == newItem.operation

}