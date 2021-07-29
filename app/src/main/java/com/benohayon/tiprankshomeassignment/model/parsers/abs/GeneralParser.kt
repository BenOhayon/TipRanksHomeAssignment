package com.benohayon.tiprankshomeassignment.model.parsers.abs

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

abstract class GeneralParser<T> {

    abstract fun parseSingleObject(jsonObject: JSONObject) : T

    fun parseSingleObject(jsonString: String) : T {
        return parseSingleObject(JSONObject(jsonString))
    }

    fun parseObjectList(jsonArray: JSONArray) : List<T> {
        val objectList = mutableListOf<T>()
        for (i in 0 until jsonArray.length()) {
            val currentJson: JSONObject = jsonArray[i] as JSONObject
            val parsedObject = parseSingleObject(currentJson)
            objectList.add(parsedObject)
        }

        return objectList
    }

    fun safeParseString(key: String, json: JSONObject) : String {
        return try {
            json.getString(key)
        } catch (e: JSONException) {
            ""
        }
    }

    fun safeParseInt(key: String, json: JSONObject) : Int {
        return try {
            json.getInt(key)
        } catch (e: JSONException) {
            0
        }
    }

    fun safeParseLong(key: String, json: JSONObject) : Long {
        return try {
            json.getLong(key)
        } catch (e: JSONException) {
            0L
        }
    }

    fun safeParseBoolean(key: String, json: JSONObject) : Boolean {
        return try {
            json.getBoolean(key)
        } catch (e: JSONException) {
            false
        }
    }

    fun hasKey(key: String, json: JSONObject) = !json.isNull(key)
}