package com.composetutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.CircleShape


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message(author = "Android", body = "Jetpack Compose"))
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg:Message){
        Row(modifier= Modifier.padding(all=8.dp)){
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = "Profile",
                modifier=Modifier.size(40.dp).clip(CircleShape)
            )
            Spacer(modifier=Modifier.width(8.dp))
            Column {
                Text(text = msg.author)
                Spacer(modifier=Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }

    }


    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard(
            msg = Message("Android", "Jetpack Compose")
        )
    }

//    @Composable
//    fun MessageCard(name :String){
//        Text(text = "Hello $name !")
//    }





}