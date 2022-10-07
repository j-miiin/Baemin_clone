package com.example.baemin.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.baemin.R
import com.example.baemin.databinding.ActivityMainBinding
import com.example.baemin.screen.main.home.HomeFragment
import com.example.baemin.screen.main.my.MyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }
            R.id.menu_my -> {
                showFragment(MyFragment.newInstance(), MyFragment.TAG)
                true
            }
            else -> false
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        // 다른 fragment는 다 가려줌
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            // 없으면 새 fragment를 추가
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
}