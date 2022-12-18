package com.jatinvashisht.algovisualizeryt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jatinvashisht.algovisualizeryt.R

@Composable
fun VisBottomBar(
    modifier: Modifier = Modifier,
    playPauseClick: () -> Unit,
    slowDownClick: () -> Unit,
    speedUpClick: () -> Unit,
    previousClick: () -> Unit,
    nextClick: () -> Unit,
    isPlaying: Boolean = false,
) {
    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(onClick = { slowDownClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleLeft,
                    contentDescription = "Slow down the speed",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { playPauseClick() }) {
                Icon(
                    imageVector =  if(!isPlaying)Icons.Default.PlayArrow else Icons.Default.Pause,
                    contentDescription = "play pause",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { speedUpClick() }) {
                Icon(
                    imageVector =  Icons.Default.Add,
                    contentDescription = "speed up",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { previousClick() }) {
                Icon(
                    imageVector =  Icons.Default.ArrowBack,
                    contentDescription = "previous step",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { nextClick() }) {
                Icon(
                    imageVector =  Icons.Default.ArrowForward,
                    contentDescription = "next step",
                    tint = MaterialTheme.colors.onSurface
                )
            }


        }
    }
}