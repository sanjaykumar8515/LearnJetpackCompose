package com.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetutorial.ui.theme.ComposeTutorialTheme

class DefaultComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                MyApp()
            }
        }
    }
}
@Composable
private fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if(shouldShowOnboarding){
        OnboardingScreen(onContinueClicked={
            shouldShowOnboarding=false
        })
    }else {
        Greetings()
    }

}

@Composable
private fun Greetings(names:List<String> = List(1000){"$it"}){
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
               LazyColumn{
                   items(names){ name->
                       Greeting(name)
                   }

                }
            }

    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember {mutableStateOf(false)}
    val extraPadding by animateDpAsState(
        targetValue = if(expanded) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    Surface(color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)){
                Text(text = "Hello,")
                Text(text = name)
                if (expanded){
                    Text(text = ("Compose ipusm color sit lazy," +
                            "padding theme elit, sed do bouncy").repeat(40),)
                }
            }
            IconButton(onClick = { expanded=!expanded }) {
                Icon(imageVector = if(expanded) Filled.ExpandLess else Filled.ExpandMore,
                    contentDescription = if(expanded){
                        stringResource(R.string.show_less)
                    }else{
                        stringResource(R.string.show_more)
                    })

            }
            OutlinedButton(onClick = {  }) {
                Text(if(expanded)  "Show more" else "Show less")

            }
        }


    }

}

@Composable
fun OnboardingScreen(
    onContinueClicked:()-> Unit
){

    Surface() {
        Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome to the Basic Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked) {
                Text("Continue")
            }

        }

    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeTutorialTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}