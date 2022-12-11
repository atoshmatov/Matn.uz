package uz.uicgroup.presentation.screens.about.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.uicgroup.presentation.screens.about.pager.InfoPager
import uz.uicgroup.presentation.screens.about.pager.ParticipantsPager

class PagerAboutAdapter(fm: FragmentManager, lf: Lifecycle) : FragmentStateAdapter(fm, lf) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InfoPager()
            else -> ParticipantsPager()
        }
    }
}