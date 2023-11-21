package com.example.groupprojectapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout chatLinearLayout;
    private EditText messageEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatLinearLayout = findViewById(R.id.chatLinearLayout);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    addMessageToChat(message, true); // User message
                    messageEditText.setText(""); // Clear input after sending
                    // Handle sending the message to the bot here
                }
            }
        });
    }

    private void addMessageToChat(String message, boolean isUserMessage) {
        TextView messageView = new TextView(this);
        messageView.setText(message);
        messageView.setTextSize(16f);
        messageView.setPadding(16, 16, 16, 16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        if (isUserMessage) {
            messageView.setBackground(ContextCompat.getDrawable(this, R.drawable.user_message_bubble));
            layoutParams.gravity = View.TEXT_ALIGNMENT_TEXT_END;
        } else {
            messageView.setBackground(ContextCompat.getDrawable(this, R.drawable.bot_message_bubble));
            layoutParams.gravity = View.TEXT_ALIGNMENT_TEXT_START;
        }

        messageView.setLayoutParams(layoutParams);
        chatLinearLayout.addView(messageView);
        // Scroll to the new message
        messageView.requestFocus();
    }

    // Method to simulate adding a bot message to the chat
    private void addBotMessage(String message) {
        addMessageToChat(message, false); // Bot message
    }
}
