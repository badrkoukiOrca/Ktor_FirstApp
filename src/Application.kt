package com.example


import com.example.Extension.ErrorMessage
import com.example.Extension.firstMessage
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.time.LocalDate


data class Model(val name: String, val items: List<Cour>, val date: LocalDate = LocalDate.of(2018, 4, 13))
data class Cour(val ID: Int, val titre: String, val complexite : Int,val actif: Boolean = true, val Chapitre_Number:Int,val formateur:String)

val model = Model("root", listOf(Cour(1, "Initier vous à kotlin",1,true,3, "Boisney Philippe"), Cour(2, "Java et API",2,true,3,"Boisney Philippe"), Cour(3, "SQLite",1,false,3,"Boisney Philippe")))
fun main(args: Array<String>) {

    val server = embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                val msg = ""
               msg.firstMessage(call)
            }
            get("/course/top") {
                val item = model.items.firstOrNull { it.ID == 1 }
                val gson = Gson()
                call.respond(gson.toJson(item))

            }
            get("/course/{key}") {
                val item = model.items.firstOrNull { it.ID == Integer.parseInt(call.parameters["key"]) }
                if (item == null){
                    val output = ""
                    output.ErrorMessage(call)
                }
                else{
                    val gson = Gson()
                    var jsonString = gson.toJson(item)
                    call.respond(jsonString)
                }

            }
            get("/course/") {

                val gson = Gson()
                var itemsJson: MutableList<Any> = mutableListOf()
                for ( item in model.items){
                    var jsonString = gson.toJson(item)
                    itemsJson.add(jsonString)
                }
                call.respond(gson.toJson(model.items))
            }
        }
    }
    server.start(wait = true)
}
