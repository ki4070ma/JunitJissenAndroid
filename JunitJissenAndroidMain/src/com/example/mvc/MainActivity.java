package com.example.mvc;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getUserIdEditText().addTextChangedListener(new EditTextWatcher());
		getPasswdEditText().addTextChangedListener(new EditTextWatcher());
		getPushButton().setOnClickListener(new PushButtonListener());

	}

	EditText getUserIdEditText() {
		return (EditText) findViewById(R.id.userIdEditText);
	}

	EditText getPasswdEditText() {
		return (EditText) findViewById(R.id.passwordEditText);
	}

	Button getPushButton() {
		return (Button) findViewById(R.id.pushButton);
	}

	class EditTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (getUserIdEditText().length() < 4
					|| getPasswdEditText().length() < 4) {
				getPushButton().setEnabled(false);
			} else {
				getPushButton().setEnabled(true);
			}

		}

	}

	// TODO 本実装に差し替える
	AuthService authService = new AuthServiceStub();

	class PushButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			String userId = getUserIdEditText().getText().toString();
			String password = getPasswdEditText().getText().toString();
			final AuthUser authUser = new AuthUser(userId, password);
			final TextView status = ((TextView) findViewById(R.id.statusTextView));
			new AsyncTask<Object, Object, String>() {

				@Override
				protected void onPostExecute(String result) {

					status.setText(result);
				}

				@Override
				protected String doInBackground(Object... params) {
					try {
						return authService.login(authUser) ? "ようこそ、"
								+ authUser.userId + "さん"
								: "ユーザIDとパスワードを正しく入力してください";
					} catch (Exception e) {
						return "システムエラー";
					}

				}
			}.execute();

		}
	}

}
