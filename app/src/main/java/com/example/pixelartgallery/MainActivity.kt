package com.example.pixelartgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pixelartgallery.ui.theme.PixelArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixelArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PixelArtGallery()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryPreview() {
    PixelArtGallery()
}

@Composable
fun PixelArtGallery(modifier: Modifier = Modifier) {
    var artworkIndex by remember{ mutableStateOf(1) }
    val artworkTitle = when(artworkIndex) {
        1 -> stringResource(R.string.artworkTitle001)
        2 -> stringResource(R.string.artworkTitle002)
        3 -> stringResource(R.string.artworkTitle003)
        4 -> stringResource(R.string.artworkTitle004)
        else -> stringResource(R.string.artworkTitle005)
    }
    val artworkDescription = "作品 00${artworkIndex}"
    val artworkImage = when(artworkIndex) {
        1 -> R.drawable.artwork001
        2 -> R.drawable.artwork002
        3 -> R.drawable.artwork003
        4 -> R.drawable.artwork004
        else -> R.drawable.artwork005
    }
    val artworkDate = when(artworkIndex) {
        1 -> stringResource(R.string.artworkDate001)
        2 -> stringResource(R.string.artworkDate002)
        3 -> stringResource(R.string.artworkDate003)
        4 -> stringResource(R.string.artworkDate004)
        else -> stringResource(R.string.artworkDate005)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtworkFrame(artworkTitle, artworkDescription, artworkImage, artworkDate, modifier = Modifier.padding(top = 100.dp))
        ButtonRow(
            onPrevClick = { if (artworkIndex - 1 >= 1) artworkIndex-- },
            onNextClick = { if (artworkIndex + 1 <= 5) artworkIndex++ }
        )
    }
}

@Composable
fun ArtworkFrame(
    artworkTitle: String,
    artworkDescription: String,
    artworkImage: Int,
    artworkDate: String,
    modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = BitmapPainter(ImageBitmap.imageResource(id = artworkImage), filterQuality = FilterQuality.None),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text = artworkTitle,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = artworkDescription,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = artworkDate,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun ButtonRow(onPrevClick: () -> Unit, onNextClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onPrevClick,
            content = { Text(text = stringResource(R.string.prev)) }
        )
        Button(
            onClick = onNextClick,
            content = { Text(text = stringResource(R.string.next)) }
        )
    }
}