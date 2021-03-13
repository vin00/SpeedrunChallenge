package com.example.androiddevchallenge

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
                Column(modifier =
                    Modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp)) {
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
    val textColor = if(darkTheme) white else gray900

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