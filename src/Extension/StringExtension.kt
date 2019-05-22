package com.example.Extension

import io.ktor.application.ApplicationCall
import io.ktor.response.respond

suspend fun String.firstMessage(call: ApplicationCall) = call.respond("Welcome to OpenClassrooms brand new server !")
suspend fun String.ErrorMessage(call: ApplicationCall) = call.respond("Error : Not found course only 1 ,2 and 3 are allowed")