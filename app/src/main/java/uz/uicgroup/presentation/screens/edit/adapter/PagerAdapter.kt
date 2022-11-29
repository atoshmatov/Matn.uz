package uz.uicgroup.presentation.screens.edit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.uicgroup.presentation.screens.edit.pager.DictionaryPager
import uz.uicgroup.presentation.screens.edit.pager.EditorPage

class PagerAdapter(fm: FragmentManager, lf: Lifecycle) : FragmentStateAdapter(fm, lf) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EditorPage()
            else -> DictionaryPager()
        }
    }
}