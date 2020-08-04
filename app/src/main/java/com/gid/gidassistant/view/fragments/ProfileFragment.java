package com.gid.gidassistant.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gid.gidassistant.R;

public class ProfileFragment extends Fragment {

    TextView textView;

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_layout, container, false);


        textView = view.findViewById(R.id.lorem_ipsum);

        //  textView.setHighlightColor(Color.TRANSPARENT);

        Spannable spannableString = new SpannableString(textView.getText());

            //    new SpannableString("more information no action random text ");


        ClickableSpan linkClick = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "more information",
                        Toast.LENGTH_SHORT).show();
                view.invalidate();
            }



            @Override
            public void updateDrawState(TextPaint ds) {
                if (textView.isPressed()) {
                    ds.setColor(Color.parseColor("#2E2E2E"));
                } else {
                    ds.setColor(Color.parseColor("#4385F5"));
                }
                textView.invalidate();
            }
        };

        String needWord = "more information";
        String text = textView.getText().toString();

        int f = text.lastIndexOf(needWord);
        int first = f + needWord.length();



        spannableString.setSpan(linkClick, f, first, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());



        return view;

    }
}
