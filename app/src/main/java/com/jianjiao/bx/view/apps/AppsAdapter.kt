package com.jianjiao.bx.view.apps

import android.view.View
import android.view.ViewGroup
import cbfg.rvadapter.RVHolder
import cbfg.rvadapter.RVHolderFactory
import com.jianjiao.bx.R
import com.jianjiao.bx.databinding.ItemAppBinding


class AppsAdapter : RVHolderFactory() {
    override fun createViewHolder(parent: ViewGroup?, viewType: Int, item: Any): RVHolder<out Any> {
        return AppsVH(inflate(R.layout.item_app, parent))
    }

    class AppsVH(itemView: View) : RVHolder<com.jianjiao.bx.bean.AppInfo>(itemView) {
        private val binding = ItemAppBinding.bind(itemView)

        override fun setContent(item: com.jianjiao.bx.bean.AppInfo, isSelected: Boolean, payload: Any?) {
            binding.icon.setImageDrawable(item.icon)
            binding.name.text = item.name

            if (item.isXpModule) {
                binding.cornerLabel.visibility = View.VISIBLE
            } else {
                binding.cornerLabel.visibility = View.INVISIBLE
            }
        }
    }
}
