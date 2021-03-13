/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun Landing(navController: NavController?) {
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.welcome_bg),
                contentDescription = null
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier
                        .requiredHeight(48.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = { }
                ) {
                    Text(text = stringResource(id = R.string.get_started))
                }
                Button(
                    modifier = Modifier
                        .requiredHeight(48.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Transparent,
                        contentColor = MaterialTheme.colors.primary
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    onClick = { navController?.navigate(login) }
                ) {
                    Text(text = stringResource(id = R.string.log_in))
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightLandingPreview() {
    MyTheme {
        Landing(navController = null)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkLandingPreview() {
    MyTheme(darkTheme = true) {
        Landing(navController = null)
    }
}
