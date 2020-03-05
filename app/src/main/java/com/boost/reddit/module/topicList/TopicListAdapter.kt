package com.boost.reddit.module.topicList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boost.reddit.databinding.ItemTopicBinding
import com.boost.reddit.model.TopicModel
import com.boost.reddit.utils.BindableAdapter

class TopicListAdapter constructor(context: Context, private val listener: OnItemClickListener) : RecyclerView.Adapter<TopicListAdapter.ViewHolder>(),
    BindableAdapter<TopicModel> {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    private var mData = ArrayList<TopicModel>()

    private val mContext = context

    override fun setData(items: List<TopicModel>) {
        mData = items as ArrayList<TopicModel>
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingView = ItemTopicBinding.inflate(mInflater, parent, false)
        return ViewHolder(bindingView.root, bindingView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < mData.size) {
            val item = mData[position]
            holder.mBinding.model = item

            holder.mBinding.root.setOnClickListener {
                listener.onItemClick(position)
            }

            holder.mBinding.btnUpVote.setOnClickListener {
                item.upVote = item.upVote + 1
                notifyItemChanged(position)
            }

            holder.mBinding.btnDownVote.setOnClickListener {
                item.downVote = item.downVote + 1
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder constructor(itemView: View, bindingView: ItemTopicBinding) : RecyclerView.ViewHolder(itemView){
        var mBinding: ItemTopicBinding = bindingView
        init {
            mBinding = bindingView
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}