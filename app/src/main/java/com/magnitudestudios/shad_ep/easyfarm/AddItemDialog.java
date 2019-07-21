package com.magnitudestudios.shad_ep.easyfarm;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

/**
 * Created by sriharivishnu on 2019-07-19.
 */


public class AddItemDialog extends Dialog implements View.OnClickListener {
    Button cancelButton, enterButton;
    private EditText codeToShare;
    private Activity activity;
    private String item;
    private boolean userSelection;
    private ProgressBar pbar;
    public AddItemDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_item_dialog);

        userSelection = false;

        codeToShare = (EditText) findViewById(R.id.enter_key);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        enterButton = (Button) findViewById(R.id.enter_button);
        pbar = (ProgressBar) findViewById(R.id.enterKeyPbar);
        pbar.setVisibility(View.GONE);

        cancelButton.setOnClickListener(this);
        enterButton.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                userSelection = false;
                dismiss();
                break;
            case R.id.enter_button:
                pbar.setVisibility(View.VISIBLE);
                dismiss();
                break;
        }
    }
    public boolean getUserSelection() {
        return userSelection;
    }
    public String getItem() {
        item = codeToShare.getText().toString();
        return item;
    }
}
