package com.example.mypokedex.image

interface IImageLoader<T> {
    fun loadInto(url: String?, container: T)
}