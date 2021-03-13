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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.theme.gray700
import com.example.androiddevchallenge.ui.theme.gray900
import com.example.androiddevchallenge.ui.theme.green
import com.example.androiddevchallenge.ui.theme.red
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun Home(navController: NavController?) {
    val darkTheme = isSystemInDarkTheme()
    val secondSurfaceBg = if (darkTheme) gray700 else Color.White
    Surface(color = secondSurfaceBg) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 32.dp),
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
        ) {
            item {
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        TabBar()
                        BalanceSection()
                        GraphSection()
                    }
                }
            }
            item {
                Surface(color = secondSurfaceBg) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PositionsSection()
                    }
                }
            }
            stockList.forEach { stock ->
                item {
                    Surface(color = secondSurfaceBg) {
                        IndividualStock(stock = stock)
                    }
                }
            }
        }
    }
}

@Composable
fun IndividualStock(stock: Stock) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(56.dp)
            .padding(horizontal = 16.dp)
    ) {
        Divider()
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StockValue(stock)
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                StockGraph(stock = stock)
            }
        }
        StockInfo(stock)
    }
}

@Composable
fun StockGraph(stock: Stock) {
    Column {
        Image(
            modifier = Modifier
                .padding(top = 16.dp),
            painter = painterResource(id = stock.graphRes),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun StockInfo(stock: Stock) {
    val isDarkTheme = isSystemInDarkTheme()
    val infoColor = if (isDarkTheme) Color.White else gray900
    Row(
        modifier = Modifier
            .offset(x = 80.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.paddingFromBaseline(top = 24.dp),
                color = infoColor,
                text = stock.symbol,
                style = MaterialTheme.typography.h3
            )
            Text(
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
                color = infoColor,
                text = stock.name,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun StockValue(stock: Stock) {
    val isDarkTheme = isSystemInDarkTheme()
    val valueColor = if (isDarkTheme) Color.White else gray900

    val isPositive = stock.change > 0
    val changeColor = if (isPositive) green else red
    val changeText = if (isPositive) "+".plus(stock.change.toString()) else stock.change.toString()
    Column {
        Text(
            modifier = Modifier.paddingFromBaseline(top = 24.dp),
            color = valueColor,
            text = stock.value,
            style = MaterialTheme.typography.body1
        )
        Text(
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 16.dp),
            color = changeColor,
            text = changeText.plus("%"),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun PositionsSection() {
    val darkTheme = isSystemInDarkTheme()
    val headerColor = if (darkTheme) Color.White else gray900
    Text(
        modifier = Modifier.paddingFromBaseline(top = 40.dp, bottom = 24.dp),
        color = headerColor,
        text = stringResource(id = R.string.positions),
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun GraphSection() {
    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            pillsList.forEach { pill ->
                item {
                    Button(
                        modifier = Modifier
                            .requiredHeight(40.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        ),
                        border = BorderStroke(1.dp, Color.White),
                        onClick = { }
                    ) {
                        Text(text = pill.name)
                        if (pill.hasDropdown) {
                            Icon(
                                Icons.Outlined.ExpandMore,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .requiredSize(16.dp)
                            )
                        }
                    }
                }
            }
        }
    )
    Image(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 32.dp)
            .fillMaxWidth(),
        painter = painterResource(id = R.drawable.home_illos),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun TabBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TopButton(R.string.account, true)
        TopButton(R.string.watchlist)
        TopButton(R.string.profile)
    }
}

@Composable
fun BalanceSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 8.dp),
            color = Color.White,
            text = stringResource(id = R.string.balance),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            modifier = Modifier.paddingFromBaseline(top = 48.dp, bottom = 24.dp),
            color = Color.White,
            text = stringResource(id = R.string.balance_amt),
            style = MaterialTheme.typography.h1
        )
        Text(
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 32.dp),
            color = green,
            text = stringResource(id = R.string.gain),
            style = MaterialTheme.typography.subtitle1
        )
        Button(
            modifier = Modifier
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
            onClick = { Unit }
        ) {
            Text(text = stringResource(id = R.string.transact))
        }
    }
}

@Composable
fun TopButton(@StringRes textRes: Int, isSelected: Boolean = false) {
    // Couldn't figure out the color of the non-selected text from the image, didn't quit AS
    // to use color picker (mac accessibility permissions)
    val textColor = if (isSelected) Color.White else Color.Gray
    Text(
        modifier = Modifier.paddingFromBaseline(top = 64.dp, bottom = 8.dp),
        color = textColor,
        text = stringResource(id = textRes),
        style = MaterialTheme.typography.button
    )
}
