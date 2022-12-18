package com.jatinvashisht.algovisualizeryt

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jatinvashisht.algovisualizeryt.algorithms.InsertionSort
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class AlgorithmViewModel @Inject constructor(
    private val insertionSort: InsertionSort
) : ViewModel(){

    var arr = mutableStateOf(intArrayOf(
        50,42, 150,56,136,568,963,21,32,12,54,5896,1,2253,6,9,8,7,7,7,8,9,5,4
    ))

    val isPlaying = mutableStateOf(false)
    val onSortingFinish = mutableStateOf(false)
    private var onDelay = 150L
    // TODO: add description this variable
    private var sortedArrayLevels = mutableListOf<List<Int>>()
    private var pause = false

    private var next = 1
    private var previous = 0

    init {
        viewModelScope.launch {
            insertionSort.sort(arr.value.clone()){modifiedArray->
                sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun onEvent(event:AlgorithmEvents){
        when(event){
            AlgorithmEvents.Next -> {
                nextClicked()
            }
            AlgorithmEvents.PlayPause -> {
                playPauseAlgorithm()
            }
            AlgorithmEvents.Previous -> {
                previousClicked()
            }
            AlgorithmEvents.SlowDown -> {
                slowDownAlgorithm()
            }
            AlgorithmEvents.SpeedUp -> {
                speedUpAlgorithm()
            }
        }
    }


    private fun nextClicked() {
        // next means goto next index, which have ceiling of size of array
        if(next < sortedArrayLevels.size){
            arr.value = sortedArrayLevels[next].toIntArray()
            next++
            previous++
        }
    }

    private fun previousClicked() {
        if(previous >= 0){
            arr.value = sortedArrayLevels[previous].toIntArray()
            next--
            previous--
        }
    }

    private fun speedUpAlgorithm() {
        if(onDelay >= 150)
            onDelay-=50
    }

    private fun slowDownAlgorithm() {
            onDelay += 50
    }

    private fun playPauseAlgorithm() {
        if(isPlaying.value){
            pause()
        }else{
            play()
        }
        isPlaying.value = !isPlaying.value
    }

    // part of the logic to save the state of the array when user clicked pause
    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for(i in sortingState until sortedArrayLevels.size){
            if(!pause){
                delay(onDelay)
                arr.value = sortedArrayLevels[i].toIntArray()
            }else{
                // this means user clicked on pause
                // save state of the array
                sortingState = i
                next = i+1
                previous = i
                return@launch
            }
        }
        onSortingFinish.value = true
        sortingState = 0
    }

    private fun pause() {
        pause = true
    }

}