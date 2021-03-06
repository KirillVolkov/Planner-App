package io.techmeskills.an02onl_plannerapp.screen.main

import androidx.lifecycle.asLiveData
import io.techmeskills.an02onl_plannerapp.models.Note
import io.techmeskills.an02onl_plannerapp.repository.NotesRepository
import io.techmeskills.an02onl_plannerapp.repository.UsersRepository
import io.techmeskills.an02onl_plannerapp.support.CoroutineViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val notesRepository: NotesRepository,
    private val usersRepository: UsersRepository
) : CoroutineViewModel() {

    val notesLiveData = notesRepository.currentUserNotesFlow.asLiveData()

    val userNameLiveData =
        usersRepository.getCurrentUserFlow().map { UserNameTitle(it.name) }.asLiveData()

    fun deleteNote(note: Note) {
        launch {
            notesRepository.deleteNote(note)
        }
    }
}

class UserNameTitle(
    val title: String
)

