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
			ContactsContract.Contacts.DISPLAY_NAME, // �̸�
			ContactsContract.Contacts.HAS_PHONE_NUMBER, // ��ȭ ��ȣ
			
	};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/*
		Cursor c=showContacts("��");
		
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
	// �̸��� ������ ���ڰ� ���Ե� ����ó ��� ��������
	// android.permission.READ_CONTACTS ������ �ʿ�
	
	private Cursor showContacts(String name) {

		// ����ó ��Ͽ��� �˻��� �÷�

		// Ȱ�� Ŀ�� �����ֱ� ������ �����Ѵ�.
		/*
		mCursor = managedQuery(
				ContactsContract.Contacts.CONTENT_URI,
				projection1,
				ContactsContract.Contacts.DISPLAY_NAME
				+ " like ?", // �̸��� ������ ���� ����
				new String[] { name + "%" },
				ContactsContract.Contacts.DISPLAY_NAME + " ASC"); // �̸����� ������������ ����
		*/
		// Ŀ�� �۾�
		//uri==>content://com.android.contacts/contacts
		mCursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				columnNameArray,
				ContactsContract.Contacts.DISPLAY_NAME
				+ " like ?", // �̸��� ������ ���� ����
				new String[] { name + "%" },
				ContactsContract.Contacts.DISPLAY_NAME + " ASC");
		

		return mCursor;
	}
	private String getCallLog(int duration) {

		// ��ȭ �α׿��� ��ȸ �÷�
		String[] projection = new String[] { 
				CallLog.Calls.TYPE, // �߽�,���� ����
				CallLog.Calls.NUMBER, // ��ȭ ��ȣ
				CallLog.Calls.DURATION // ��ȭ �ð�
		};
		// projection���� ������ �÷� �ε���
		final int TYPE_INDEX = 0;
		final int NUMBER_INDEX = 1;
		final int DURATION_INDEX = 2;

		// ���� �ð� �̻��� �α׸� �˻�
		String selection = CallLog.Calls.DURATION + " >= " + duration;
		// ������������ ����
		String order = CallLog.Calls.DURATION + " desc";

		// �˻� ����
		Cursor c = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI,
				projection, selection, null, order);
		// ����� ������ null�� ��ȯ
		if (c == null)
			return "";

		// �˻� ��� 1 ���� ���ڿ��� ����
		StringBuffer buff = new StringBuffer();
		Resources res = getResources();
		if (c.moveToFirst()) {
			do {
				// ���� �Ǵ� ���� ���θ� Ȯ��
				if (c.getInt(0) == CallLog.Calls.INCOMING_TYPE) {
					buff.append(res.getString(R.string.incomming));
				} else { // CallLog.Calls.OUTGOING_TYPE
					buff.append(res.getString(R.string.outgoing));
				}
				buff.append(" : ");
				// ��ȭ ��ȣ
				buff.append(c.getString(NUMBER_INDEX));
				buff.append(" : ");
				// ��ȭ �ð�
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

	// �̵��� �̹����� �����ϱ�
	private Uri putMedia(String name, Bitmap bitmap) {
		// �ܺ� �̵��
		Uri media = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

		// �����ϴ� �̹����� �Ӽ��� ����
		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		values.put(MediaStore.Images.Media.DESCRIPTION, "description");
		values.put(MediaStore.Images.Media.DATA, "/sdcard/test.jpg");
		

		// �̹����� ������ ������ Ȯ���Ͽ� �ش� Uri�� �˻�
		
		ContentResolver r = getContentResolver();
		Uri imageUri = r.insert(media, values);
		Toast.makeText(this, imageUri+"", Toast.LENGTH_LONG).show();
		try {
			// �˻��� Uri���� ��� ��Ʈ���� ��ȸ
			OutputStream out = r.openOutputStream(imageUri);
			// ��� ��Ʈ���� �����͸� JPEG�� ���
			bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
		} catch (FileNotFoundException e) {
			Log.e("HelloContentsProviders", e.toString());
		}
		return imageUri;
	}
	// �ϸ�ũ URL�� �а� 1 ���� ���ڿ��� ����
	private String listAllBookmarks() {

		// Browser �����ϴ� ����� �޼��带 ���
		Cursor c = android.provider.Browser.getAllBookmarks(getContentResolver());

		// Ŀ�� ������ URL�� �����ϴ� �÷��� �ε����� ȹ��
		int index = c.getColumnIndex(Browser.BookmarkColumns.URL);
		StringBuffer buff = new StringBuffer();
		if (c.moveToFirst()) {
			do {
				// Ŀ������ URL�� �����Ͽ� ���ڿ��� �߰�
				buff.append(c.getString(index));
				buff.append("\n");
			} while (c.moveToNext());
		}
		c.close();
		return buff.toString();
	}
}