package com.example.mygallerydemo;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	public final static int PICK_PHOTO_CODE = 1046;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void  onSelectPhoto(View view) {
		Intent intent = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PICK_PHOTO_CODE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data != null) {
	        Uri photoUri = data.getData();
	        // Do something with the photo based on Uri
	        Bitmap selectedImage = null;
			try {
				selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
			} catch (FileNotFoundException e ) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (selectedImage != null) {
		        ImageView ivPreview = (ImageView) findViewById(R.id.ivPreview);
		        ivPreview.setImageBitmap(selectedImage);   
			}
	    }
	}
}
