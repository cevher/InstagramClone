package android.galileo.instagramclone;

import android.app.Application;
import com.parse.Parse;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("OTXXuBiPYm28F4kYbisI2jpFHWOxiPRTCzmFg0iV")
                // if defined
                .clientKey("NetavNJcwGmezR6LtLg0EvPXSpFDj3KsSsXWSmtm")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
