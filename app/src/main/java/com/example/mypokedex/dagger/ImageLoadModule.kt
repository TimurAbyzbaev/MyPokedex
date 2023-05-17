package com.example.mypokedex.dagger

import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.example.mypokedex.image.GlideImageLoader
import com.example.mypokedex.image.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ImageLoadModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}