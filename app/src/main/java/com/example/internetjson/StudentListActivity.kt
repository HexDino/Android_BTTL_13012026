package com.example.internetjson

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentListAdapter
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        // Set title
        title = getString(R.string.app_name)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        progressBar = findViewById(R.id.progressBar)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentListAdapter(emptyList()) { student ->
            // Handle item click - navigate to detail activity
            val intent = Intent(this, StudentDetailActivity::class.java).apply {
                putExtra("student_id", student.id)
                putExtra("student_mssv", student.mssv)
                putExtra("student_hoten", student.hoten)
                putExtra("student_ngaysinh", student.ngaysinh)
                putExtra("student_email", student.email)
                putExtra("student_thumbnail", student.thumbnail)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Setup search functionality
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            adapter.filter(query)
        }

        // Load students from API
        loadStudents()
    }

    private fun loadStudents() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        RetrofitClient.instance.getStudents().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                if (response.isSuccessful) {
                    val students = response.body()
                    if (students != null) {
                        adapter.updateStudents(students)
                        Toast.makeText(
                            this@StudentListActivity,
                            "Đã tải ${students.size} sinh viên",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@StudentListActivity,
                        "Lỗi: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                Toast.makeText(
                    this@StudentListActivity,
                    "Lỗi kết nối: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}

