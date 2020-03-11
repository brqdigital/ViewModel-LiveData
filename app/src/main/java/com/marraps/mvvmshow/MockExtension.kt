package com.marraps.mvvmshow

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun <T> InputStream.toClass(aClass: Class<T>): T =
    Gson().fromJson(BufferedReader(InputStreamReader(this)), aClass)