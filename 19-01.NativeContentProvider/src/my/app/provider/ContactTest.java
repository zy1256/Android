package my.app.provider;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.*;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ContactTest extends ListActivity {
	/** Called when the activity is first created. */
	/*
	static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
		Contacts._ID, // 0
		Contacts.DISPLAY_NAME, // 1
		Contacts.STARRED, // 2
		Contacts.TIMES_CONTACTED, // 3
		Contacts.CONTACT_PRESENCE, // 4
		Contacts.PHOTO_ID, // 5
		Contacts.LOOKUP_KEY, // 6
		Contacts.HAS_PHONE_NUMBER, // 7
	};
	 */
	String[] columnNameArray = new String[] { 
			ContactsContract.Contacts._ID, // ID
			ContactsContract.Contacts.DISPLAY_NAME, // 이름
			ContactsContract.Contacts.HAS_PHONE_NUMBER, // 전화 번호
			
	};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*
		Cursor c=showContacts("김");
		
		ListAdapter adapter = new SimpleCursorAdapter(this, 
				// Use a temp,late that displays a text view
				R.layout.main_list_row, 
				// Give the cursor to the list adatper
				c, 
				// Map the NAME column in the people database to...
				columnNameArray ,
				// The "text1" view defined in the XML template
				new int[]{R.id.col1,R.id.col2,R.id.col3},0); 
		setListAdapter(adapter);
		*/
		String logStr=getCallLog(100);
		
		Toast.makeText(this, logStr, Toast.LENGTH_LONG).show();
		printContacts();
		
		putMedia();
		String logStr1=listAllBookmarks();
		Toast.makeText(this, logStr1, Toast.LENGTH_LONG).show();
		
	}
	private Cursor mCursor;
	// 이름에 지정한 문자가 포함된 연락처 목록 가져오기
	// android.permission.READ_CONTACTS 선언이 필요
	
	private Cursor showContacts(String name) {

		// 연락처 목록에서 검색할 컬럼

		// 활동 커서 수명주기 관리를 수행한다.
		/*
		mCursor = managedQuery(
				ContactsContract.Contacts.CONTENT_URI,
				projection1,
				ContactsContract.Contacts.DISPLAY_NAME
				+ " like ?", // 이름에 지정한 문자 포함
				new String[] { name + "%" },
				ContactsContract.Contacts.DISPLAY_NAME + " ASC"); // 이름으로 오름차순으로 정렬
		*/
		// 커서 작업
		//uri==>content://com.android.contacts/contacts
		mCursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				columnNameArray,
				ContactsContract.Contacts.DISPLAY_NAME
				+ " like ?", // 이름에 지정한 문자 포함
				new String[] { name + "%" },
				ContactsContract.Contacts.DISPLAY_NAME + " ASC");
		

		return mCursor;
	}
	private String getCallLog(int duration) {

		// 통화 로그에서 만회 컬럼
		String[] projection = new String[] { 
				CallLog.Calls.TYPE, // 발신,수신 유형
				CallLog.Calls.NUMBER, // 전화 번호
				CallLog.Calls.DURATION // 통화 시간
		};
		// projection에서 지정한 컬럼 인덱스
		final int TYPE_INDEX = 0;
		final int NUMBER_INDEX = 1;
		final int DURATION_INDEX = 2;

		// 일정 시간 이상의 로그를 검색
		String selection = CallLog.Calls.DURATION + " >= " + duration;
		// 내림차순으로 정렬
		String order = CallLog.Calls.DURATION + " desc";

		// 검색 실행
		Cursor c = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI,
				projection, selection, null, order);
		// 결과가 없으면 null을 반환
		if (c == null)
			return "";

		// 검색 결과 1 개의 문자열로 조립
		StringBuffer buff = new StringBuffer();
		Resources res = getResources();
		if (c.moveToFirst()) {
			do {
				// 수신 또는 전송 여부를 확인
				if (c.getInt(0) == CallLog.Calls.INCOMING_TYPE) {
					buff.append(res.getString(R.string.incomming));
				} else { // CallLog.Calls.OUTGOING_TYPE
					buff.append(res.getString(R.string.outgoing));
				}
				buff.append(" : ");
				// 전화 번호
				buff.append(c.getString(NUMBER_INDEX));
				buff.append(" : ");
				// 통화 시간
				buff.append(c.getInt(DURATION_INDEX));
				buff.append("\n");
			} while (c.moveToNext());
		}
		c.close();
		return buff.toString();
	}
	public void printContacts() {
		Cursor contacts = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (contacts.moveToNext()) {
			String contactId = contacts.getString(contacts
					.getColumnIndex(ContactsContract.Contacts._ID));  
			Log.e("------", "-----------------------------------------");
			Log.d("contacts", "contactId: " + contactId);
			String displayName = contacts.getString(contacts
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			Log.d("contacts", "display_name: " + displayName);
			
			String hasPhoneNumber = contacts
			.getString(contacts
					.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
			Log.d("contacts", "hasPhone: " + hasPhoneNumber);
			if (hasPhoneNumber.equals("1")) {
				Cursor phones = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ " = " + contactId, null, null);
				while (phones.moveToNext()) {
					String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					Log.d("contacts", "phoneNumber: " + phoneNumber);
				}

				phones.close();
			}
			Cursor emails = getContentResolver().query(
					ContactsContract.CommonDataKinds.Email.CONTENT_URI,
					null,
					ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "
					+ contactId, null, null);
			while (emails.moveToNext()) {
				String emailAddress = emails
				.getString(emails
						.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
				Log.d("contacts", "emailAddress: " + emailAddress);
			}
			emails.close();
			Log.e("------", "-----------------------------------------");
		}
		contacts.close();
	} 
	private void putMedia() {
		Bitmap b = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_btn_speak_now);		
		putMedia("Icon" + new java.util.Date().toString(), b);
	}

	// 미디어스토어에 이미지를 저장하기
	private Uri putMedia(String name, Bitmap bitmap) {
		// 외부 미디어
		Uri media = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

		// 저장하는 이미지의 속성을 지정
		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		values.put(MediaStore.Images.Media.DESCRIPTION, "description");
		values.put(MediaStore.Images.Media.DATA, "/sdcard/test.jpg");
		

		// 이미지를 저장할 공간을 확보하여 해당 Uri를 검색
		
		ContentResolver r = getContentResolver();
		Uri imageUri = r.insert(media, values);
		Toast.makeText(this, imageUri+"", Toast.LENGTH_LONG).show();
		try {
			// 검색된 Uri에서 출력 스트림을 만회
			OutputStream out = r.openOutputStream(imageUri);
			// 출력 스트림에 데이터를 JPEG로 출력
			bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
		} catch (FileNotFoundException e) {
			Log.e("HelloContentsProviders", e.toString());
		}
		return imageUri;
	}
	// 북마크 URL를 읽고 1 개의 문자열로 조립
	private String listAllBookmarks() {

		// Browser 정의하는 도우미 메서드를 사용
		Cursor c = android.provider.Browser.getAllBookmarks(getContentResolver());

		// 커서 사이의 URL을 저장하는 컬럼의 인덱스를 획득
		int index = c.getColumnIndex(Browser.BookmarkColumns.URL);
		StringBuffer buff = new StringBuffer();
		if (c.moveToFirst()) {
			do {
				// 커서에서 URL을 추출하여 문자열에 추가
				buff.append(c.getString(index));
				buff.append("\n");
			} while (c.moveToNext());
		}
		c.close();
		return buff.toString();
	}
}