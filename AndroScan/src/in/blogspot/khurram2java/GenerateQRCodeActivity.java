package in.blogspot.khurram2java;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class GenerateQRCodeActivity extends Activity implements OnClickListener {
	private EditText qrInput;
	private String LOG_TAG = "GenerateQRCode";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcodegenerate);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);

		qrInput = (EditText) findViewById(R.id.qrInput);

	}
	 @Override
	public boolean onTouchEvent(MotionEvent event) {
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(qrInput.getWindowToken(), 0);
		return super.onTouchEvent(event);
	}
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			String qrInputText = qrInput.getText().toString();
			Log.v(LOG_TAG, qrInputText);

			// Find screen size
			WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
			Display display = manager.getDefaultDisplay();
			Point point = new Point();
			display.getSize(point);
			int width = point.x;
			int height = point.y;
			int smallerDimension = width < height ? width : height;
			smallerDimension = smallerDimension * 3 / 4;

			// Encode with a QR Code image
			QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText, null,
					Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(),
					smallerDimension);
			try {
				Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
				ImageView myImage = (ImageView) findViewById(R.id.imageView1);
				myImage.setImageBitmap(bitmap);

			} catch (WriterException e) {
				e.printStackTrace();
			}

			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(qrInput.getWindowToken(), 0);
			break;


		// More buttons go here (if any) ...

		}
	}

}