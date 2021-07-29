package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.*
import com.benohayon.tiprankshomeassignment.model.models.Author
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class AuthorParser : GeneralParser<Author>() {

    override fun parseSingleObject(jsonObject: JSONObject): Author {
        val name =
            if (hasKey(KEY_NAME, jsonObject)) safeParseString(KEY_NAME, jsonObject)
            else null

        val slug =
            if (hasKey(KEY_SLUG, jsonObject)) safeParseString(KEY_SLUG, jsonObject)
            else null

        val type =
            if (hasKey(KEY_TYPE, jsonObject)) safeParseString(KEY_TYPE, jsonObject)
            else null

        val bio =
            if (hasKey(KEY_BIO, jsonObject)) safeParseString(KEY_BIO, jsonObject)
            else null

        val created =
            if (hasKey(KEY_CREATED, jsonObject)) safeParseString(KEY_CREATED, jsonObject)
            else null

        val image =
            if (hasKey(KEY_IMAGE, jsonObject)) {
                val imageJson = jsonObject.getJSONObject(KEY_IMAGE)
                ImageParser().parseSingleObject(imageJson)
            } else null

        return Author(name, slug, type, bio, image, created)
    }
}