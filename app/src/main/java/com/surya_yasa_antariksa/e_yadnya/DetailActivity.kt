        package com.surya_yasa_antariksa.e_yadnya.activity

        import androidx.appcompat.app.AppCompatActivity
        import android.os.Bundle
        import android.widget.TextView
        import androidx.recyclerview.widget.LinearLayoutManager
        import androidx.recyclerview.widget.RecyclerView
        import com.surya_yasa_antariksa.e_yadnya.R
        import com.surya_yasa_antariksa.e_yadnya.adapter.OrderAdapter
        import com.surya_yasa_antariksa.e_yadnya.model.OrderItemModel
        import com.surya_yasa_antariksa.e_yadnya.model.OrderModel

        class DetailActivity : AppCompatActivity() {

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_detail)

            }
        }
