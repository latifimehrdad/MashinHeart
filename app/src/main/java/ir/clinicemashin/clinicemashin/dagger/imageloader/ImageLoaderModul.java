package ir.clinicemashin.clinicemashin.dagger.imageloader;

import com.nostra13.universalimageloader.core.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageLoaderModul {

    @Provides
    public ImageLoader getImageLoader(){
        return ImageLoader.getInstance();
    }
}
