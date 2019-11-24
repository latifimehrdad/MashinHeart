package com.androidha.mashinheart.dagger.imageloader;

import com.nostra13.universalimageloader.core.ImageLoader;

import dagger.Component;

@Component(modules = ImageLoaderModul.class)
public interface ImageLoaderComponent {
    ImageLoader getImageLoader();
}
