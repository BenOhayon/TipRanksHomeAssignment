package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.KEY_SRC
import com.benohayon.tiprankshomeassignment.model.models.Thumbnail
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class ThumbnailParser : GeneralParser<Thumbnail>() {

    override fun parseSingleObject(jsonObject: JSONObject): Thumbnail {
        val src =
            if (hasKey(KEY_SRC, jsonObject)) safeParseString(KEY_SRC, jsonObject)
            else null

        return Thumbnail(src)
    }
}