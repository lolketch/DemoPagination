package com.example.testpagination.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.testpagination.data.MainRepository
import com.example.testpagination.data.remote.model.MyCharacter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val characters = MutableLiveData<PagingData<MyCharacter>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        compositeDisposable.add(
            mainRepository.getCharacters()
                //.cachedIn(viewModelScope)
                .subscribe {
                    characters.postValue(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}