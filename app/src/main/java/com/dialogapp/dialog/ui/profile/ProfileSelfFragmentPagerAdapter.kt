package com.dialogapp.dialog.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dialogapp.dialog.ui.profile.following.FollowingFragment

class ProfileSelfFragmentPagerAdapter(fm: FragmentManager, private val username: String)
    : FragmentPagerAdapter(fm) {

    private val TAB_TITLES = arrayOf("POSTS", "FAVORITES", "FOLLOWING")

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> ProfilePostsFragment.newInstance(username)
            1 -> FavoritesFragment()
            2 -> FollowingFragment.newInstance(username, true)
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }
}