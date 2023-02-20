package com.surya_yasa_antariksa.e_yadnya.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.surya_yasa_antariksa.e_yadnya.R
import java.text.DecimalFormat


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val spinner: Spinner = view.findViewById(R.id.mySpinner)
        val priceView: TextView = view.findViewById(R.id.price_view)
        val jumlahEditText: EditText = view.findViewById(R.id.edit_jumlah)

        val items = resources.getStringArray(R.array.mySpinnerItems)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.visibility = View.VISIBLE

        spinner.adapter = adapter

        var selectedPrice = 0

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedPrice = when (position) {
                    0 -> 7000
                    1 -> 9000
                    2 -> 1000
                    else -> 0
                }
                val jumlah = jumlahEditText.text.toString().toIntOrNull() ?: 1
                val total = selectedPrice * jumlah
                val formatter = DecimalFormat("Rp#,###")
                val formattedPrice = formatter.format(total)
                val priceText = getString(R.string.price_text, formattedPrice)
                priceView.text = priceText
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        jumlahEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val jumlah = s.toString().toIntOrNull() ?: 1
                val total = selectedPrice * jumlah
                val formatter = DecimalFormat("Rp#,###")
                val formattedPrice = formatter.format(total)
                val priceText = getString(R.string.price_text, formattedPrice)
                priceView.text = priceText
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action__bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notification -> Toast.makeText(context, "notifikasi belum tersedia", Toast.LENGTH_SHORT).show()
            R.id.keranjang -> Toast.makeText(context, "keranjang belum tersedia", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
