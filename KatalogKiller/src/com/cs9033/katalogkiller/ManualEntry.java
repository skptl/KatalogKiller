package com.cs9033.katalogkiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class ManualEntry extends Activity{
	
	private EditText edtInputText;
	private Button btnSubmitInput;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entermanualinput);

		bindComponents();
	}

	private void bindComponents() {

		edtInputText = (EditText) findViewById(R.id.editText1);
		btnSubmitInput = (Button) findViewById(R.id.button1);
		
		btnSubmitInput.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});

	}
}
