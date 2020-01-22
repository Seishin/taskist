package me.seishin.taskist.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tasks")
data class Task (
    var title: String,
    var isChecked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo(name = "createdAt")
    private var createdAt: Long = System.currentTimeMillis()
    fun getCreatedAt() = createdAt
    fun setCreatedAt(createdAt: Long) { this.createdAt = createdAt }

    var updatedAt: Long = System.currentTimeMillis()

    fun updateTitle(text: String) {
        this.title = text
        updatedAt = System.currentTimeMillis()
    }

    fun updateCheckedState(isChecked: Boolean) {
        this.isChecked = isChecked
        updatedAt = System.currentTimeMillis()
    }
}