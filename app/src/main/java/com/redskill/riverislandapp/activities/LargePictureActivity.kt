package com.redskill.riverislandapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.redskill.riverislandapp.R
import com.squareup.picasso.Picasso

class LargePictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_large_picture)

        val largePicture_ImageView = findViewById<ImageView>(R.id.largePicture_imageView2)
        val prodid = intent.getStringExtra("SELECTED_ITEM_PRODID")
        Picasso.get().load("http://riverisland.scene7.com/is/image/RiverIsland/${prodid}_main").into(largePicture_ImageView);

    }
}
