package com.work.hany.imagelistproject

import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by hany on 2018. 4. 13..
 */


class GalleryFetchr {

    fun urlBytes(strUrl: String): ByteArray {

        val url = URL(strUrl)
        val connection = url.openConnection() as HttpURLConnection

        try {
         val outPutStream = ByteArrayOutputStream()
            val inputStream = connection.inputStream

            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                var exceptionMsg = StringBuilder().append(connection.responseMessage)
                        .append(": with ").append(strUrl).toString()
                throw IOException(exceptionMsg)
            }

            var bytesRead = 0
            val buffer = ByteArray(1024)

            while (true) {
                bytesRead = inputStream.read(buffer)
                if(bytesRead <= 0) break
                outPutStream.write(buffer,0,bytesRead)
            }
            outPutStream.close()
            return outPutStream.toByteArray()

        }finally {
            connection.disconnect()

        }
    }

    @Throws(IOException::class)
    fun urlString(strUrl: String): String{
        return  String(urlBytes(strUrl))
    }
}

