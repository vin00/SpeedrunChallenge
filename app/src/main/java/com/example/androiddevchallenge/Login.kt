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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.ui.theme.white
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun Login(navController: NavController?) {
    val darkTheme = isSystemInDarkTheme()
    val backgroundColor = if (darkTheme) gray900 else white
    Surface(color = backgroundColor) {
        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.login_bg),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier =
                    Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp)
                ) {
                    LoginInputField(Icons.Outlined.Email, R.string.email_address)
                    LoginInputField(Icons.Outlined.Password, R.string.password)
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .requiredHeight(48.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.onPrimary
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        ),
                        onClick = { navController?.navigate(home) }
                    ) {
                        Text(text = stringResource(id = R.string.log_in))
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_back),
                    modifier = Modifier
                        .width(200.dp)
                        .paddingFromBaseline(top = 152.dp),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun LoginInputField(icon: ImageVector, @StringRes placeholder: Int) {
    val darkTheme = isSystemInDarkTheme()
    val textColor = if (darkTheme) white else gray900

    // TODO : Fix label margin on the left, too large from the icon
    // Looks like it's a hidden field :(
    var field: String by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = field,
        onValueChange = { field = it },
        modifier = Modifier
            .requiredHeight(56.dp)
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = textColor
        ),
        label = {
            Text(
                text = stringResource(id = placeholder),
                style = MaterialTheme.typography.body1
            )
        },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.requiredSize(24.dp)
            )
        },
        singleLine = true,
    )
}

@Preview("Light Login Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        Login(navController = null)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkLoginPreview() {
    MyTheme(darkTheme = true) {
        Login(navController = null)
    }
}
