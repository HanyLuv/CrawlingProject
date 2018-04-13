package com.work.hany.imagelistproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Element


class MainActivity : AppCompatActivity() {
    private val baseURL = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        FetchTask(object : FetchTask.OnGalleryResponseListener {
            override fun onSuccess(galleries: ArrayList<Gallery>) {
                val adapter = GalleryViewAdapter(galleries)
                recyclerview.layoutManager = LinearLayoutManager(baseContext)
                recyclerview.adapter = adapter
            }
        }).execute(baseURL)
    }
}


class FetchTask(private val listener: FetchTask.OnGalleryResponseListener) : AsyncTask<String, Void, ArrayList<Gallery>>() {

    interface OnGalleryResponseListener {
        fun onSuccess(galleries: ArrayList<Gallery>)
    }

    override fun doInBackground(vararg params: String): ArrayList<Gallery> {
        var galleries = arrayListOf<Gallery>()
        val strUrl = params[0]

        val doc = Jsoup.connect(strUrl).method(Connection.Method.GET).execute().parse()
//        var elements = doc.select("div.gallery-wrap.exhibitionrepeater div a img.picture")

        var elements = doc.select("div.gallery-wrap.exhibitionrepeater")
                .first()
                .getElementsByClass("gallery-item-group exitemrepeater")


        for (element: Element in elements){
            val title = element.getElementsByTag("a").text()
            val imagePath = element.getElementsByTag("img").attr("src")
            val detailPath = element.getElementsByTag("a").attr("href")

            galleries.add(Gallery(title,imagePath,detailPath))
        }

        return  galleries
    }

    override fun onPostExecute(result: ArrayList<Gallery>) {
        super.onPostExecute(result)
        listener.onSuccess(result)
    }


}

