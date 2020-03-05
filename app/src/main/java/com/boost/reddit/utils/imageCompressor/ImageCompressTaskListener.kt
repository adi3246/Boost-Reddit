package com.boost.reddit.utils.imageCompressor

import java.io.File

interface ImageCompressTaskListener {

    fun onComplete(compressed: ArrayList<File>)
    fun onError(error: Throwable)
}