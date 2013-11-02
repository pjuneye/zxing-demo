package cn.angrycode.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.CaptureActivity;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int QR_CODE = 1000;

	private TextView mResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mResult = (TextView)findViewById(R.id.qr_code_result);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.qr_code_btn:
			startActivityForResult(new Intent(this, CaptureActivity.class),
					QR_CODE);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == QR_CODE) {
			String result = data.getExtras().getString("result");
			
			Log.i(TAG, "scan result = "+result);
			if (TextUtils.isEmpty(result)) {
				showToast();
				return;
			}
			mResult.setText(result);
		}
	}

	private void showToast() {
		Toast.makeText(getApplicationContext(), R.string.scan_retry,
				Toast.LENGTH_SHORT).show();
	}
}
