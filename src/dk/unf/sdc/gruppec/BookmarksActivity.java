package dk.unf.sdc.gruppec;

import java.util.ArrayList;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import dk.unf.sdc.gruppec.data.Bookmark;
import dk.unf.sdc.gruppec.database.BookmarkManagerDatabaseConnection;

public class BookmarksActivity extends Activity {

	private Button addBookmarkShortButton;
	private ArrayList<Bookmark> listOfBookmarks;
	private LinearLayout buttonHolderLayout;
	private TextView noBookmarksView;
	private BookmarkManagerDatabaseConnection dataBase;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookmarks);

		addBookmarkShortButton = (Button) findViewById(R.id.add_bookmarks_shortcut_button);
		addBookmarkShortButton.setOnClickListener(addBookmarkShortListener);
		buttonHolderLayout = (LinearLayout) findViewById(R.id.bookmarks_buttons_linear_layout);
		noBookmarksView = (TextView) findViewById(R.id.no_bookmarks_text_view);
		dataBase = new BookmarkManagerDatabaseConnection(
				getApplicationContext());

	}

	private OnClickListener addBookmarkShortListener = new OnClickListener() {

		public void onClick(View v) {
			Intent intentAddBM = new Intent(getApplicationContext(),
					AddBActivity.class);
			intentAddBM.putExtra("dk.unf.sdc.gruppec.edit", false);
			startActivityForResult(intentAddBM, 2);

		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		
		buttonHolderLayout.removeAllViews();
		listOfBookmarks = new ArrayList<Bookmark>(dataBase.getBookmarks());

		if (listOfBookmarks.size() > 0) {
			buttonHolderLayout.setVisibility(View.VISIBLE);
			noBookmarksView.setVisibility(View.GONE);
		} else {
			buttonHolderLayout.setVisibility(View.GONE);
			noBookmarksView.setVisibility(View.VISIBLE);
		}

		if (listOfBookmarks.size() != 0) {
			for (int i = 0; i < listOfBookmarks.size(); i++) {
				Button newButton = new Button(this);
				newButton.setText(listOfBookmarks.get(i).getName());
				newButton.setTextSize(25);
				newButton.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				final int buttonListenerHandler = i + 1;

				OnClickListener newButtonListener = new OnClickListener() {

					public void onClick(View v) {
						Intent intentEdit = new Intent(getApplicationContext(),
								AddBActivity.class);
						intentEdit.putExtra("dk.unf.sdc.gruppec.bmid",
								buttonListenerHandler);
						intentEdit.putExtra("dk.unf.sdc.gruppec.edit", true);
						startActivityForResult(intentEdit, 0);

					}
				};

				newButton.setOnClickListener(newButtonListener);

				buttonHolderLayout.addView(newButton);
			}

		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_bookmarks, menu);
		return true;
	}

}
