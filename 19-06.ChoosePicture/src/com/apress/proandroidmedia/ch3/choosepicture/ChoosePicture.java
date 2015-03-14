package com.apress.proandroidmedia.ch3.choosepicture;

import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoosePicture extends Activity implements OnClickListener {

	ImageView chosenImageView;
	Button choosePicture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		chosenImageView = (ImageView) this.findViewById(R.id.ChosenImageView);
		choosePicture = (Button) this.findViewById(R.id.ChoosePictureButton);

		choosePicture.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent choosePictureIntent = new Intent();
		choosePictureIntent.setAction(Intent.ACTION_PICK);
		choosePictureIntent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(choosePictureIntent,0);
	
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		

		if (resultCode == Activity.RESULT_OK) {
			Uri imageFileUri = intent.getData();
			Toast.makeText(getApplicationContext(), imageFileUri+"", 5000).show();
//			

			try {
				ContentResolver cr=getContentResolver();
				Cursor cursor=cr.query(imageFileUri, null, null, null, null);
				cursor.moveToFirst();
				String path=
						cursor.getString(
								cursor.getColumnIndex(MediaStore.Images.Media.DATA));
				Log.e(path, path);
				InputStream in=cr.openInputStream(imageFileUri);
				//Cursor cursor=cr.query(imageFileUri,null,null,null,null);
				Bitmap bmp = BitmapFactory.decodeStream(in);
				chosenImageView.setImageBitmap(bmp);
			} catch (Exception e) {
				Log.v("ERROR", e.toString());
			}
		}
	}
}
