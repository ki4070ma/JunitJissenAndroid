package com.example.mvc;

import java.io.IOException;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	MainActivity activity;
	EditText userIdEditText;
	EditText passwdEditText;
	Button pushButton;
	TextView statusTextView;

	// public MainActivityTest(Class<MainActivity> activityClass) {
	// super(activityClass);
	// }

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		userIdEditText = (EditText) activity.findViewById(R.id.userIdEditText);
		passwdEditText = (EditText) activity
				.findViewById(R.id.passwordEditText);
		pushButton = (Button) activity.findViewById(R.id.pushButton);
		statusTextView = (TextView) activity.findViewById(R.id.statusTextView);
	}

	public void testPreconditions() throws Exception {
		assertEquals(userIdEditText.getText().toString(), "");
		assertEquals(passwdEditText.getText().toString(), "");
		assertFalse(pushButton.isEnabled());
		assertEquals(statusTextView.getText().toString(), "");
	}

	public void testHappyPath() throws Exception {
		// 1. userIdEditTextを選択し、「duke」を入力する
		TouchUtils.clickView(this, userIdEditText);
		sendKeys("D U K E");
		assertEquals("duke", userIdEditText.getText().toString());

		// 2. passwdEditTextを選択し、「3micro」と入力する
		TouchUtils.clickView(this, passwdEditText);
		sendKeys("3 M I C R O");
		assertEquals("3micro", passwdEditText.getText().toString());

		// 3. プッシュボタンをクリックする
		TouchUtils.clickView(this, pushButton);

		// 4. ステータスバーに「ようこそ、dukeさん」と表示されていることを確認する
		assertEquals("ようこそ、dukeさん", statusTextView.getText().toString());

	}

	public void testユーザ認証に失敗する() throws Exception {
		// 1. userIdEditTextを選択し、「duke」を入力する
		TouchUtils.clickView(this, userIdEditText);
		sendKeys("D U K E");
		assertEquals("duke", userIdEditText.getText().toString());

		// 2. passwdEditTextを選択し、「0r4c1e」と入力する
		TouchUtils.clickView(this, passwdEditText);
		sendKeys("0 R 4 C 1 E");
		assertEquals("0r4c1e", passwdEditText.getText().toString());

		// 3. プッシュボタンをクリックする
		TouchUtils.clickView(this, pushButton);

		// 4. ステータスバーに「ユーザIDとパスワードを正しく入力してください」と表示されていることを確認する
		assertEquals("ユーザIDとパスワードを正しく入力してください", statusTextView.getText()
				.toString());

	}

	public void testユーザ認証で例外が発生する() throws Exception {
		activity.authService = new AuthService() {

			@Override
			public boolean login(AuthUser arg0) throws IOException {
				throw new IOException("Error");

			}
		};

		// 1. userIdTextを選択し、「duke」と入力する
		TouchUtils.clickView(this, userIdEditText);
		sendKeys("D U K E");
		assertEquals("duke", userIdEditText.getText().toString());

		// 2. passwdEditText を選択し、「3micro」と入力する
		TouchUtils.clickView(this, passwdEditText);
		sendKeys("3 M I C R O");
		assertEquals("3micro", passwdEditText.getText().toString());

		// 3. プッシュボタンをクリックする
		TouchUtils.clickView(this, pushButton);

		// 4. ステータスバーに「システムエラー」と表示されていることを確認する
		assertEquals("システムエラー", statusTextView.getText().toString());

	}
}
