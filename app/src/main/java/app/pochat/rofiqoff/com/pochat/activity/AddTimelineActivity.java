package app.pochat.rofiqoff.com.pochat.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import app.pochat.rofiqoff.com.pochat.R;

public class AddTimelineActivity extends AppCompatActivity implements View.OnClickListener{

	private Toolbar toolbar;
	private Button bDate;
	private Button bTime;
	private EditText eDate;
	private EditText eTime;
	private ImageView iPoster;

	private int IMG_RESULT = 1;
	private String image;

	private int mYear, mMonth, mDay, mHour, mMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_timeline);
		toolbar = (Toolbar) findViewById(R.id.toolbarActivityTimeLine);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Add Timeline");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		bDate = (Button) findViewById(R.id.btn_date);
		bTime = (Button) findViewById(R.id.btn_time);
		eDate = (EditText) findViewById(R.id.editText_date);
		eTime = (EditText) findViewById(R.id.editText_Time);
		iPoster = (ImageView) findViewById(R.id.imagePoster);

		bDate.setOnClickListener(this);
		bTime.setOnClickListener(this);
		iPoster.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if (v == bDate){
			final Calendar calendar = Calendar.getInstance();
			mYear = calendar.get(Calendar.YEAR);
			mMonth = calendar.get(Calendar.MONTH);
			mDay = calendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
					eDate.setText(dayOfMonth + "-" +(month + 1)+"-"+year);
				}
			}, mYear, mMonth, mDay);
			datePickerDialog.show();
		}

		if (v == bTime){
			final Calendar calendar = Calendar.getInstance();
			mHour = calendar.get(Calendar.HOUR_OF_DAY);
			mMinute = calendar.get(Calendar.MINUTE);

			TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					eTime.setText(hourOfDay+":"+minute);
				}
			}, mHour, mMinute, false);
			timePickerDialog.show();
		}

		if (v == iPoster){
			startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMG_RESULT);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			if (requestCode == IMG_RESULT && resultCode == RESULT_OK && null != data){

				Uri URI = data.getData();
				String[] FILE = {MediaStore.Images.Media.DATA};

				Cursor cursor = AddTimelineActivity.this.getContentResolver().query(URI, FILE, null, null, null);

				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(FILE[0]);
				image = cursor.getString(columnIndex);
				cursor.close();

				iPoster.setImageBitmap(BitmapFactory.decodeFile(image));
			}
		} catch (Exception e){
			Toast.makeText(AddTimelineActivity.this, "Please Try again "+e, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_add_timeline, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.save){

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
