package com.example.internetjson

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var thumbnailImageView: ImageView
    private lateinit var hotenTextView: TextView
    private lateinit var mssvTextView: TextView
    private lateinit var ngaysinhTextView: TextView
    private lateinit var emailTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        // Enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize views
        thumbnailImageView = findViewById(R.id.detailThumbnailImageView)
        hotenTextView = findViewById(R.id.detailHotenTextView)
        mssvTextView = findViewById(R.id.detailMssvTextView)
        ngaysinhTextView = findViewById(R.id.detailNgaysinhTextView)
        emailTextView = findViewById(R.id.detailEmailTextView)

        // Get student data from intent
        val studentHoten = intent.getStringExtra("student_hoten") ?: ""
        val studentMssv = intent.getStringExtra("student_mssv") ?: ""
        val studentNgaysinh = intent.getStringExtra("student_ngaysinh") ?: ""
        val studentEmail = intent.getStringExtra("student_email") ?: ""
        val studentThumbnail = intent.getStringExtra("student_thumbnail") ?: ""

        // Display student data
        hotenTextView.text = studentHoten
        mssvTextView.text = studentMssv
        ngaysinhTextView.text = studentNgaysinh
        emailTextView.text = studentEmail

        // Load thumbnail using Glide - add base URL if needed
        val imageUrl = if (studentThumbnail.startsWith("http")) {
            studentThumbnail
        } else {
            "https://lebavui.io.vn$studentThumbnail"
        }
        
        Glide.with(this)
            .load(imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(thumbnailImageView)

        // Set title
        supportActionBar?.title = studentHoten
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

