package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.KEY_DESCRIPTION
import com.benohayon.tiprankshomeassignment.KEY_TITLE
import com.benohayon.tiprankshomeassignment.model.models.Seo
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class SeoParser : GeneralParser<Seo>() {

    override fun parseSingleObject(jsonObject: JSONObject): Seo {
        val title =
            if (hasKey(KEY_TITLE, jsonObject)) safeParseString(KEY_TITLE, jsonObject)
            else null

        val description =
            if (hasKey(KEY_DESCRIPTION, jsonObject)) safeParseString(KEY_DESCRIPTION, jsonObject)
            else null

        return Seo(title, description)
    }
}