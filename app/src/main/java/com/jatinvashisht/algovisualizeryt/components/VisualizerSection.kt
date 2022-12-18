package com.jatinvashisht.algovisualizeryt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VisualizerSection(
    modifier: Modifier = Modifier,
    arr: IntArray,
) {
    BoxWithConstraints(modifier = modifier) {
        val maxHeight = maxHeight - 75.dp // 75 is height of bottom bar
        val itemWidth = remember{
            maxWidth / arr.size - 8.dp // each item width is maxWidth/arr.size
            // 8 dp is space between each item
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            arr.forEach {
                Box(
                    modifier = Modifier.height(if(it.dp > maxHeight) maxHeight else it.dp)
                        .width(itemWidth)
                        .background(MaterialTheme.colors.secondary)
                )
            }
        }
    }
}