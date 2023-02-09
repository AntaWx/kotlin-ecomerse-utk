package com.surya_yasa_antariksa.e_yadnya.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.surya_yasa_antariksa.e_yadnya.R
import com.surya_yasa_antariksa.e_yadnya.adapter.ViewPagerAdapter


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action__bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notification -> Toast.makeText(context, "notifikasi belum tersedia", Toast.LENGTH_SHORT).show()
            R.id.keranjang -> Toast.makeText(context, "keranjang belum tersedia", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}