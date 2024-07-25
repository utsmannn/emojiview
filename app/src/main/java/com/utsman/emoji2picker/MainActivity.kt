package com.utsman.emoji2picker

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.utsman.emoji2picker.ui.theme.Emoji2pickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Emoji2pickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.height(300.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    AndroidView(
        factory = { ctx ->
            EmojiPickerView(ctx)
                .apply {
                    emptyPlaceholderRes = R.layout.empty_emoji
                    categoryPlaceholderRes = R.layout.category_emoji
                    emojiGridColumns = 7

                    setOnEmojiPickedListener {
                        println("clicked on -> ${it.emoji}")
                    }
                }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Emoji2pickerTheme {
        Greeting("Android")
    }
}