package com.example.facebooksimilar.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebooksimilar.R
import com.example.facebooksimilar.data.Dessert
import com.example.facebooksimilar.data.Fruit
import com.example.facebooksimilar.data.ListItem
import com.example.facebooksimilar.data.Person
import com.example.facebooksimilar.data.Shortcut

@Composable
fun NavigationDrawerScreen(randomItems: List<ListItem>, shortcuts: List<Shortcut>) {

    Column(modifier = Modifier.background(Color(0xFFEEEEEE))) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
            )
            Text(text = "Hamed", modifier = Modifier.padding(8.dp))
        }
        Text(text = "Your shortcuts", modifier = Modifier.padding(8.dp))

        LazyRow {
            items(randomItems) {
                var shape: Shape = RoundedCornerShape(8.dp)
                var resId: Int = 0
                var title: String? = null
                when (it::class) {
                    Dessert::class -> {
                        resId = (it as Dessert).resId
                        title = (it as Dessert).title
                    }

                    Fruit::class -> {
                        resId = (it as Fruit).resId
                        title = (it as Fruit).name
                    }

                    Person::class -> {
                        shape = CircleShape
                        resId = (it as Person).resId
                        title = (it as Person).name
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape)
                    )
                    title?.let { Text(text = it, fontSize = 12.sp) }
                }
            }
        }
        Text(text = "All shortcuts", modifier = Modifier.padding(8.dp))

        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
            items(shortcuts) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(8.dp)
                        .clickable {  }
                ) {
                    Icon(
                        painter = painterResource(id = it.resId),
                        contentDescription = "",
                        tint = it.tint
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = it.title, fontSize = 12.sp)
                }
            }
        }

    }

}