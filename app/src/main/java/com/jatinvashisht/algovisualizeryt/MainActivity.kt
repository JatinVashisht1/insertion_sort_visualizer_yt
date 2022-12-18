package com.jatinvashisht.algovisualizeryt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jatinvashisht.algovisualizeryt.components.VisBottomBar
import com.jatinvashisht.algovisualizeryt.components.VisualizerSection
import com.jatinvashisht.algovisualizeryt.ui.theme.AlgoVisualizerYtTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgoVisualizerYtTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    VisualizerScreenContent()
                }
            }
        }
    }
}

@Composable
fun VisualizerScreenContent(
    algorithmViewModel: AlgorithmViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            VisualizerSection(
                arr = algorithmViewModel.arr.value,
                modifier = Modifier.fillMaxWidth()
            )
            val isPlaying = algorithmViewModel.isPlaying.value
            val isFinished = algorithmViewModel.onSortingFinish.value

            VisBottomBar(
                playPauseClick = { algorithmViewModel.onEvent(AlgorithmEvents.PlayPause) },
                slowDownClick = { algorithmViewModel.onEvent(AlgorithmEvents.SlowDown) },
                speedUpClick = { algorithmViewModel.onEvent(AlgorithmEvents.SpeedUp) },
                previousClick = { algorithmViewModel.onEvent(AlgorithmEvents.Previous) },
                nextClick = { algorithmViewModel.onEvent(AlgorithmEvents.Next) },
                modifier = Modifier.fillMaxWidth()
                    .height(75.dp),
                isPlaying = if(isFinished) !isFinished else isPlaying
            )
        }
    }
}
