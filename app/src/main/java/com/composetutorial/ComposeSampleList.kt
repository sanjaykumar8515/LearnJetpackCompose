package com.composetutorial

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetutorial.ui.theme.ComposeTutorialTheme

class ComposeSampleList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
              MyApp()
            }
        }
    }
}
@Composable
private fun MyApp(){
    var shouldShowOnboarding by rememberSaveable{
        mutableStateOf(true)
    }

    if(shouldShowOnboarding){
        OnboardingScreen1(onContinueClicked = {shouldShowOnboarding=false})
    }else{
        Greetings2()
    }
}


@Composable
fun Greetings2(name: List<String> = List(1000){"$it"}) {
   LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
   items(items = name){ name ->
       Greeting1(name)
   }
   }
}

@Composable
private fun Greeting1(name:String){
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}
@Composable
private fun CardContent(name:String){
    var expanded by remember{ mutableStateOf(false)}
    Row(modifier = Modifier
        .padding(12.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )) {

        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
                Text(text = "Hello, ")
                Text(text = name,
                style=MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
            if(expanded){
                Text(text = ("Compose ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy.").repeat(4),)
            }
            }
        IconButton(onClick = { expanded= !expanded}) {
            Icon(imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if(expanded){
                    stringResource(R.string.show_less)
                }else{
                    stringResource(R.string.show_more)
                })

        }
    }
}

@Composable
private fun OnboardingScreen1(onContinueClicked:()->Unit){
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the basic tutorial!")
            Button( modifier=Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked) {
                Text("Continue")
            }

        }
    }
}
@Preview(showBackground = true,
widthDp = 320,
uiMode = UI_MODE_NIGHT_YES,
name="DefaultPreviewDark")
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview2() {
    ComposeTutorialTheme {
        Greetings2()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview1(){
    ComposeTutorialTheme {
        OnboardingScreen1(onContinueClicked = {})
    }
}