package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText emailInput;
    private EditText contactInput;
    private EditText messageInput;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mDatabase = FirebaseDatabase.getInstance().getReference("feedback");

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        contactInput = findViewById(R.id.contactInput);
        messageInput = findViewById(R.id.messageInput);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> submitFeedback());
    }

    private void submitFeedback() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String contact = contactInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        //validate
        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

    // p key
        String feedbackId = mDatabase.push().getKey();

        Feedback feedback = new Feedback(feedbackId, name, email, contact, message);

        // firebase
        if (feedbackId != null) {
            mDatabase.child(feedbackId).setValue(feedback)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(FeedbackActivity.this,
                                "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(FeedbackActivity.this,
                                    "Error submitting feedback: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show()
                    );
        }
    }
}