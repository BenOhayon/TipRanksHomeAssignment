package com.benohayon.tiprankshomeassignment.model.parsers

import com.benohayon.tiprankshomeassignment.KEY_MARKET
import com.benohayon.tiprankshomeassignment.KEY_TICKER
import com.benohayon.tiprankshomeassignment.model.models.Stock
import com.benohayon.tiprankshomeassignment.model.parsers.abs.GeneralParser
import org.json.JSONObject

class StockParser : GeneralParser<Stock>() {

    override fun parseSingleObject(jsonObject: JSONObject): Stock {
        val ticker =
            if (hasKey(KEY_TICKER, jsonObject)) safeParseString(KEY_TICKER, jsonObject)
            else null

        val market =
            if (hasKey(KEY_MARKET, jsonObject)) safeParseString(KEY_MARKET, jsonObject)
            else null

        return Stock(ticker, market)
    }
}