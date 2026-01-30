package com.mudassir.taskmanagerapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val imageUri: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
) : Parcelable

