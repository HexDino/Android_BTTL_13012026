package com.example.internetjson

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("_id")
    val id: String,
    
    @SerializedName("mssv")
    val mssv: String,
    
    @SerializedName("hoten")
    val hoten: String,
    
    @SerializedName("ngaysinh")
    val ngaysinh: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("thumbnail")
    val thumbnail: String
)

