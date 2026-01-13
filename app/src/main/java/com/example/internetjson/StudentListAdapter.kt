package com.example.internetjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class StudentListAdapter(
    private var students: List<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    private var filteredStudents: List<Student> = students

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        val hotenTextView: TextView = itemView.findViewById(R.id.hotenTextView)
        val mssvTextView: TextView = itemView.findViewById(R.id.mssvTextView)

        fun bind(student: Student) {
            hotenTextView.text = student.hoten
            mssvTextView.text = "MSSV: ${student.mssv}"

            // Load image using Glide - add base URL if needed
            val imageUrl = if (student.thumbnail.startsWith("http")) {
                student.thumbnail
            } else {
                "https://lebavui.io.vn${student.thumbnail}"
            }
            
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(thumbnailImageView)

            itemView.setOnClickListener {
                onItemClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_list, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(filteredStudents[position])
    }

    override fun getItemCount(): Int = filteredStudents.size

    fun updateStudents(newStudents: List<Student>) {
        students = newStudents
        filteredStudents = newStudents
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredStudents = if (query.isEmpty()) {
            students
        } else {
            students.filter { student ->
                student.hoten.contains(query, ignoreCase = true) ||
                        student.mssv.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}

