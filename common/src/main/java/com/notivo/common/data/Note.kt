package com.notivo.common.data

sealed class Note {
    abstract val id: String
    abstract var title: String
    abstract var folderId: String?
}

data class TextNote(
    override val id: String,
    override var title: String,
    override var folderId: String?,
    var text: String?
) : Note()

data class ChecklistNote(
    override val id: String,
    override var title: String,
    override var folderId: String?,
    val items: List<ChecklistItem>,
) : Note()
