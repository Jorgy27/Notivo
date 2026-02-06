package com.notivo.common.data

sealed class Note {
    abstract val id: String
    abstract val title: String
    abstract val folderId: String?
}

data class TextNote(
    override val id: String,
    override val title: String,
    override val folderId: String?,
    val text: String?
) : Note()

data class ChecklistNote(
    override val id: String,
    override val title: String,
    override val folderId: String?,
    val items: List<ChecklistItem>,
) : Note()
