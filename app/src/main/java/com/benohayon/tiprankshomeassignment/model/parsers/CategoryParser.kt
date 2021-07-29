package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.KEY_ID
import com.benohayon.tiprankshomeassignment.KEY_SLUG
import com.benohayon.tiprankshomeassignment.KEY_TITLE
import com.benohayon.tiprankshomeassignment.model.models.Category
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class CategoryParser : GeneralParser<Category>() {

    override fun parseSingleObject(jsonObject: JSONObject): Category {
        val id =
            if (hasKey(KEY_ID, jsonObject)) safeParseLong(KEY_ID, jsonObject)
            else -1L

        val slug =
            if (hasKey(KEY_SLUG, jsonObject)) safeParseString(KEY_SLUG, jsonObject)
            else null

        val title =
            if (hasKey(KEY_TITLE, jsonObject)) safeParseString(KEY_TITLE, jsonObject)
            else null

        return Category(id, slug, title)
    }
}