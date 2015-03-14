package in.blogspot.khurram2java;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView tvStatus;
	TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvStatus = (TextView) findViewById(R.id.tvStatus);
		tvResult = (TextView) findViewById(R.id.tvResult);

		Button scanBtn = (Button) findViewById(R.id.btnScan);

		// in some trigger function e.g. button press within your code you
		// should add:
		scanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {

					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1)
							.show();

				}

			}
		});

	}
	// In the same activity you�ll need the following to retrieve the results:
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
			} else if (resultCode == RESULT_CANCELED) {
				tvStatus.setText("Press a button to start a scan.");
				tvResult.setText("Scan cancelled.");
			}
		}
	}
	//qr code generator �� �̵�
	public void movetoGen(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(),
				in.blogspot.khurram2java.GenerateQRCodeActivity.class);
		startActivity(intent);
	}

}
