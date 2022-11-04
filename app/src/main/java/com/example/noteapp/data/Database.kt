package com.example.noteapp.data

object Database {
    var listNote: MutableList<Note> = mutableListOf(
        Note(
            id = 0,
            title = "Note 0",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In commodo efficitur ipsum ut tristique. Vivamus nec mi purus. Morbi nec interdum mauris. Phasellus et vulputate nunc. Nulla aliquet rutrum sapien, nec facilisis erat hendrerit eget. Vestibulum nec pretium nisi. Ut rhoncus varius nibh. Integer dapibus sollicitudin neque, porttitor imperdiet diam."
        ),
        Note(
            id = 1,
            title = "Note 1",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In commodo efficitur ipsum ut tristique. Vivamus nec mi purus. Morbi nec interdum mauris. Phasellus et vulputate nunc. Nulla aliquet rutrum sapien, nec facilisis erat hendrerit eget. Vestibulum nec pretium nisi. Ut rhoncus varius nibh. Integer dapibus sollicitudin neque, porttitor imperdiet diam."
        ),
        Note(
            id = 2,
            title = "Note 2",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In commodo efficitur ipsum ut tristique. Vivamus nec mi purus. Morbi nec interdum mauris. Phasellus et vulputate nunc. Nulla aliquet rutrum sapien, nec facilisis erat hendrerit eget. Vestibulum nec pretium nisi. Ut rhoncus varius nibh. Integer dapibus sollicitudin neque, porttitor imperdiet diam."
        ),
        Note(
            id = 3,
            title = "Note 3",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In commodo efficitur ipsum ut tristique. Vivamus nec mi purus. Morbi nec interdum mauris. Phasellus et vulputate nunc. Nulla aliquet rutrum sapien, nec facilisis erat hendrerit eget. Vestibulum nec pretium nisi. Ut rhoncus varius nibh. Integer dapibus sollicitudin neque, porttitor imperdiet diam."
        ),
        Note(
            id = 4,
            title = "Note 4",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In commodo efficitur ipsum ut tristique. Vivamus nec mi purus. Morbi nec interdum mauris. Phasellus et vulputate nunc. Nulla aliquet rutrum sapien, nec facilisis erat hendrerit eget. Vestibulum nec pretium nisi. Ut rhoncus varius nibh. Integer dapibus sollicitudin neque, porttitor imperdiet diam."
        ),
    )

    fun addNote(note: Note) {
        listNote.add(note)
    }

    fun editNote(note: Note) {
        val id = note.id
        listNote[id] = note
    }
}