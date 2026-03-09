package com.bhst.dailydango.hiragana_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HiraganaDetailViewModel @Inject constructor(

): ViewModel() {
    private val _selectedRow = MutableStateFlow<String>("")
    val selectedRow: StateFlow<String> = _selectedRow.asStateFlow()

    fun updateSelectedRow(row: String) {
        _selectedRow.update { row }
    }
}