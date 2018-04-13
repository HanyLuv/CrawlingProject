package com.work.hany.imagelistproject

/**
 * Created by hany on 2018. 4. 13..
 */

data class Gallery(val title: String, private val imagePath: String, private val detailPath: String) {
    //todo: 코드 중복되는거 묶자.

    val imageUrl: String
        get() : String {
            return StringBuilder().append("http://www.gettyimagesgallery.com").append(imagePath).toString()
        }

    val detailUrl: String
        get() : String {
            return StringBuilder().append("http://www.gettyimagesgallery.com").append(detailPath).toString()
        }
}