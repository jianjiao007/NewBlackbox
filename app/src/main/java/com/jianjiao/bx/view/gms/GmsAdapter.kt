package com.jianjiao.bx.view.gms

import android.view.View
import android.view.ViewGroup
import cbfg.rvadapter.RVHolder
import cbfg.rvadapter.RVHolderFactory
import com.jianjiao.bx.R
import com.jianjiao.bx.databinding.ItemGmsBinding
import com.jianjiao.bx.bean.GmsBean

class GmsAdapter : RVHolderFactory() {
    override fun createViewHolder(parent: ViewGroup?, viewType: Int, item: Any): RVHolder<out Any> {
        return GmsVH(inflate(R.layout.item_gms, parent))
    }

    class GmsVH(itemView: View) : RVHolder<com.jianjiao.bx.bean.GmsBean>(itemView) {
        private val binding = ItemGmsBinding.bind(itemView)

        override fun setContent(item: com.jianjiao.bx.bean.GmsBean, isSelected: Boolean, payload: Any?) {
            binding.tvTitle.text = item.userName
            binding.checkbox.isChecked = item.isInstalledGms
            binding.checkbox.setOnCheckedChangeListener  { buttonView, _ ->
                if (buttonView.isPressed) {
                    binding.root.performClick()
                }
            }
        }
    }
}
