package org.chen.cibrary.cache

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Cache {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    var key: String = ""


    var data: ByteArray? = null


}