package com.work.hany.imagelistproject

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gallery_row.view.*

/**
 * Created by hany on 2018. 4. 13..
 */
class GalleryViewAdapter(private val galleries: ArrayList<Gallery>): RecyclerView.Adapter<GalleryViewAdapter.GalleryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val rowView = parent.inflate(R.layout.gallery_row)
        return GalleryViewHolder(rowView)
    }

    override fun getItemCount() = galleries.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
       holder.bind(galleries[position])
    }


    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gallery: Gallery){
            Log.e("HANY_TAG","road!!! "+gallery.imageUrl)
            Glide.with(itemView.context).load(gallery.imageUrl).into(itemView.galleryimage)
            itemView.titleview.text = gallery.title
        }

    }


    /*** extensions */
   private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


}

