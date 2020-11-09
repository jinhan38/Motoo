package io.motoo.www.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BottomNaviViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    var fragmentList = ArrayList<Fragment>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addItem(fragment: Fragment){
        fragmentList.add(fragment)

    }
}