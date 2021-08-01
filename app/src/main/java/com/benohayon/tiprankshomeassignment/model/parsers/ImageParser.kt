package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.KEY_HEIGHT
import com.benohayon.tiprankshomeassignment.KEY_SRC
import com.benohayon.tiprankshomeassignment.KEY_WIDTH
import com.benohayon.tiprankshomeassignment.model.models.Image
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class ImageParser : GeneralParser<Image>() {

    override fun parseSingleObject(jsonObject: JSONObject): Image {
        val src =
            if (hasKey(KEY_SRC, jsonObject)) safeParseString(KEY_SRC, jsonObject)
            else null

        val width =
            if (hasKey(KEY_WIDTH, jsonObject)) safeParseInt(KEY_WIDTH, jsonObject)
            else null

        val height =
            if (hasKey(KEY_WIDTH, jsonObject)) safeParseInt(KEY_HEIGHT, jsonObject)
            else null

        return Image(src, width, height)
    }
}