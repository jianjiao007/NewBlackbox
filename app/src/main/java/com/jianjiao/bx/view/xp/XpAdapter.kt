package com.jianjiao.bx.view.xp

import android.view.View
import android.view.ViewGroup
import cbfg.rvadapter.RVHolder
import cbfg.rvadapter.RVHolderFactory
import com.jianjiao.bx.R
import com.jianjiao.bx.bean.XpModuleInfo
import com.jianjiao.bx.databinding.ItemXpBinding

class XpAdapter : RVHolderFactory() {
    override fun createViewHolder(parent: ViewGroup?, viewType: Int, item: Any): RVHolder<out Any> {
        return XpVH(inflate(R.layout.item_xp, parent))
    }

    class XpVH(itemView: View) : RVHolder<com.jianjiao.bx.bean.XpModuleInfo>(itemView) {
        private val binding = ItemXpBinding.bind(itemView)

        override fun setContent(item: com.jianjiao.bx.bean.XpModuleInfo, isSelected: Boolean, payload: Any?) {
            binding.icon.setImageDrawable(item.icon)
            binding.name.text = item.name
            binding.desc.text = item.desc
            binding.enable.isChecked = item.enable
            binding.enable.setOnCheckedChangeListener { buttonView, _ ->
                if (buttonView.isPressed) {
                    binding.root.performClick()
                }
            }
        }
    }
}
