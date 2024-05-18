package com.uuranus.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.MyScheduleNavType

class MyScheduleType : NavType<MyScheduleNavType?>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): MyScheduleNavType? = bundle.parcelable(key)

    override fun put(bundle: Bundle, key: String, value: MyScheduleNavType?) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): MyScheduleNavType {
        val decoded = Uri.decode(value)
        return pokemonJsonAdapter.fromJson(decoded)!!
    }

    companion object {
        private val pokemonJsonAdapter =
            Moshi.Builder().build().adapter(MyScheduleNavType::class.java)

        fun encodeToString(myScheduleNavType: MyScheduleNavType): String {
            return Uri.encode(pokemonJsonAdapter.toJson(myScheduleNavType))
        }
    }
}
