package jp.techacademy.kenji.sparepartsmanagement

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    val titleIds = listOf(R.string.tab_title_IN, R.string.tab_title_OUT, R.string.tab_title_DBedit, R.string.tab_title_Test)
    val fragments = listOf(InFragment(),OutFragment(),DBeditFragment(),TestFragment())

    override fun getItemCount(): Int{
        return fragments.size
    }

    override fun createFragment(position:Int): Fragment{
        return fragments[position]
    }
}