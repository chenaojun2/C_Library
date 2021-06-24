package org.chen.cibrary.cache

import androidx.annotation.NonNull
import androidx.room.PrimaryKey

class Cache {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    var key: String = ""


    var data: ByteArray? = null


}