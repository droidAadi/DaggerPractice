package com.example.daggerpractice.di.module;

import com.example.daggerpractice.di.module.image.GlideModule;
import com.example.daggerpractice.di.module.network.RetrofitModule;

import dagger.Module;

/**
 * All the application level dependencies should be added here.
 */
@Module (includes = {GlideModule.class, RetrofitModule.class})
public class AppModule {


    /*@Provides
    static RequestOptions provideRequestOptions(){

        return RequestOptions.placeholderOf(R.drawable.white_background).
                error(R.drawable.white_background);
    }

    @Provides
    static RequestManager providesGlideInstance(Application application,
                                                RequestOptions requestOptions){
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
*/
/*
    *//**
     * As per documentation, use static with @Provides if the method does not depend upon any
     * kind of instance.
     *
     * @return
     *//*
    @Provides
    static String providesString(){
        return "This is a Test String";
    }

    *//**
     * Application object is available here because we have bound the application object in
     * AppComponent when the component was constructed and also it was declared inside modules in
     * Component.Hence, it is available across other modules as well.
     *
     * @param application
     * @return
     *//*
    @Provides
    static boolean providesApp(Application application){
        return application == null;
    }*/
}
