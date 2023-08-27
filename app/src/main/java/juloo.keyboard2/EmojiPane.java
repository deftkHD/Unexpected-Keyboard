package juloo.keyboard2;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.ContextThemeWrapper;
import android.view.View;

import androidx.emoji2.emojipicker.EmojiPickerView;

public class EmojiPane {

    private static KeyboardData emojiKeyboardLayout;

    private final View view;
    private final EmojiPickerView emojiPicker;
    private final Keyboard2View keyboardView;

    public EmojiPane(Context context, Config config) {
        Context ctx = new ContextThemeWrapper(context, config.theme);
        view = View.inflate(ctx, R.layout.emoji_pane, null);
        emojiPicker = view.findViewById(R.id.emoji_picker);
        keyboardView = view.findViewById(R.id.keyboard);
        keyboardView.setKeyboard(getEmojiKeyboardLayout(context));
    }

    public void show(InputMethodService service) {
        service.setInputView(view);
        emojiPicker.setOnEmojiPickedListener(emojiViewItem -> {
            Config config = Config.globalConfig();
            KeyValue kv = KeyValue.getKeyByName(emojiViewItem.getEmoji());
            config.handler.key_up(kv, Pointers.Modifiers.EMPTY);
        });
    }

    private static KeyboardData getEmojiKeyboardLayout(Context context) {
        if (emojiKeyboardLayout == null) {
            emojiKeyboardLayout = KeyboardData.load(context.getResources(), R.xml.emoji_key_layout);
        }
        return emojiKeyboardLayout;
    }

}
