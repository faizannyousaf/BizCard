package com.example.bizcard

import android.os.Bundle
import android.util.Log
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    createBizCard()
                }
            }
        }
    }
}

@Composable
fun createBizCard(){
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(10.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ){
            Column(modifier = Modifier
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                createImageProfile(modifier = Modifier)
                Divider(modifier = Modifier.padding(top = 10.dp),2.dp)
                createInfo()
                Button(onClick = {
                    Log.d("ContentButton","Clicked")
                                 buttonClickedState.value = !buttonClickedState.value
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
                    Text(text = "Portfolio")
                }
                if (buttonClickedState.value){
                    Content()
                }
                else{
                    Box(){

                    }
                }
            }

        }
    }
}

//@Preview
@Composable
fun Content() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
            color = MaterialTheme.colorScheme.contentColorFor(Color.White),
            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray )) {
            Portfolio(data = listOf("Project 1", "Project 2 ", "project 3", "Project 4",
                "Project 1", "Project 2 ", "project 3", "Project 4"))

        }
    }

}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){ item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){
                Row (modifier = Modifier
                    .padding(8.dp)
                    // .background(MaterialTheme.colorScheme.contentColorFor(Color.White))
                    .padding(6.dp)){
                    createImageProfile(modifier = Modifier.size(100.dp))
                    Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "Greatt", style = MaterialTheme.typography.bodyMedium)
                    }

                }
            }
        }
    }

}

@Composable
private fun createInfo() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Faizan Yousaf",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Blue
        )
        Text(
            text = "Android compose programmer",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = "@FaizanYousaf",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(2.dp)
        )

    }
}

@Composable
private fun createImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizCardTheme {
        createBizCard()
    }
}