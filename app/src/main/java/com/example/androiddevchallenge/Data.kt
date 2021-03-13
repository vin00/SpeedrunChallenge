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

data class Pill(val name: String, val hasDropdown: Boolean = false)

val pillsList = listOf(
    Pill("Week", true),
    Pill("ETFs"),
    Pill("Stocks"),
    Pill("Funds"),
    Pill("Bonds"),
    Pill("Mutual Funds")
)

data class Stock(val symbol: String, val name: String, val value: String, val change: Float, val graphRes: Int)

val stockList = listOf(
    Stock("ALK", "Alaska Air Group, Inc.", "$7,918", -0.54f, R.drawable.home_alk),
    Stock("BA", "Boeing Co.", "$1,293", 4.18f, R.drawable.home_ba),
    Stock("DAL", "Delta Airlines Inc.", "$893.50", -0.54f, R.drawable.home_dal),
    Stock("EXPE", "Expedia Group Inc.", "$12,301", 2.51f, R.drawable.home_exp),
    Stock("EADSY", "Airbus SE", "$12,301", 1.38f, R.drawable.home_eadsy),
    Stock("JBLU", "Jetblue Airways Corp.", "$8,521", 1.56f, R.drawable.home_jblu),
    Stock("MAR", "Marriot International Inc.", "$521", 2.75f, R.drawable.home_mar),
    Stock("CCL", "Carnival Corp", "$5,481", 0.14f, R.drawable.home_ccl),
    Stock("RCL", "Royal Caribbean Cruises", "$9,184", 1.69f, R.drawable.home_rcl),
    Stock("TRVL", "Travelocity Inc.", "$654", 3.23f, R.drawable.home_trvl),
)
