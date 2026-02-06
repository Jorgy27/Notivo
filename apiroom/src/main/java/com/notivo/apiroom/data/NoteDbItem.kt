package com.notivo.apiroom.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.notivo.common.data.NoteType

@Entity(tableName = "notes")
data class NoteDbItem(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "folderId")
    val folderId: String?,
    @ColumnInfo(name = "type")
    val type: NoteType?,
)
