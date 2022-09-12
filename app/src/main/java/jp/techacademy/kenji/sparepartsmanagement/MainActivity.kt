package jp.techacademy.kenji.sparepartsmanagement

import android.content.Context
import android.os.Bundle

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {


    }


    private  val viewPagerAdapter by lazy{ ViewPagerAdapter(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2.apply{
            adapter = viewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = viewPagerAdapter.itemCount
        }
        TabLayoutMediator(tabLayout, viewPager2){tab, position ->
            tab.setText(viewPagerAdapter.titleIds[position])
        }.attach()

//        setSupportActionBar(findViewById(R.id.toolbar))



//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }



    }



}
