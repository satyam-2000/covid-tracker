package com.internshala.covidtracker

data class Modal (
    val districtName:String,
    val active:String,
    val confirmed:String,
    val recovered:String,
    val deseased:String,
    val deltaConfirmed:String,
    val deltaRecovered:String,
    val deltaDeseased:String
        )
