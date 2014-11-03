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
		// 1. userIdEditText��I�����A�uduke�v����͂���
		TouchUtils.clickView(this, userIdEditText);
		sendKeys("D U K E");
		assertEquals("duke", userIdEditText.getText().toString());

		// 2. passwdEditText��I�����A�u3micro�v�Ɠ��͂���
		TouchUtils.clickView(this, passwdEditText);
		sendKeys("3 M I C R O");
		assertEquals("3micro", passwdEditText.getText().toString());

		// 3. �v�b�V���{�^�����N���b�N����
		TouchUtils.clickView(this, pushButton);

		// 4. �X�e�[�^�X�o�[�Ɂu�悤�����Aduke����v�ƕ\������Ă��邱�Ƃ��m�F����
		assertEquals("�悤�����Aduke����", statusTextView.getText().toString());

	}

	public void test���[�U�F�؂Ɏ��s����() throws Exception {
		// 1. userIdEditText��I�����A�uduke�v����͂���
		TouchUtils.clickView(this, userIdEditText);
		sendKeys("D U K E");
		assertEquals("duke", userIdEditText.getText().toString());

		// 2. passwdEditText��I�����A�u0r4c1e�v�Ɠ��͂���
		TouchUtils.clickView(this, passwdEditText);
		sendKeys("0 R 4 C 1 E");
		assertEquals("0r4c1e", passwdEditText.getText().toString());

		// 3. �v�b�V���{�^�����N���b�N����
		TouchUtils.clickView(this, pushButton);

		// 4. �X�e�[�^�X�o�[�Ɂu���[�UID�ƃp�X���[�h�𐳂������͂��Ă��������v�ƕ\������Ă��邱�Ƃ��m�F����
		assertEquals("���[�UID�ƃp�X���[�h�𐳂������͂��Ă�������", statusTextView.getText()
				.toString());

	}

	public void test���[�U�F�؂ŗ�O����������() throws Exception {
		activity.authService = new AuthService() {

			@Override
			public boolean login(AuthUser arg0) throws IOException {
				throw new IOException("Error");

			}
		};

		// 1. userIdText��I�����A�uduke�v�Ɠ��͂���
		TouchUtils.clickView(this, userIdEditText);
		sendKeys("D U K E");
		assertEquals("duke", userIdEditText.getText().toString());

		// 2. passwdEditText ��I�����A�u3micro�v�Ɠ��͂���
		TouchUtils.clickView(this, passwdEditText);
		sendKeys("3 M I C R O");
		assertEquals("3micro", passwdEditText.getText().toString());

		// 3. �v�b�V���{�^�����N���b�N����
		TouchUtils.clickView(this, pushButton);

		// 4. �X�e�[�^�X�o�[�Ɂu�V�X�e���G���[�v�ƕ\������Ă��邱�Ƃ��m�F����
		assertEquals("�V�X�e���G���[", statusTextView.getText().toString());

	}
}
