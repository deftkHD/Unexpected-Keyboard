package juloo.keyboard2;

import android.app.Application;

import androidx.emoji2.text.EmojiCompat;

public class Keyboard2App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EmojiCompat.init(this); // Init emoji compatibility in the background
    }
}
