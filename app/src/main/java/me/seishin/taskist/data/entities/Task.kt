package me.seishin.taskist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tasks")
data class Task (
    var title: String,
    var isChecked: Boolean,
    val createdAt: Long,
    var updatedAt: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}