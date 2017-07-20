package com.extremekod.extremespace;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        config.useCompass 		= false;
        config.useAccelerometer = false;
		config.useWakelock 		= true;

		extremeSpace.width  = 1920;
		extremeSpace.height = 1080;

		initialize(new extremeSpace(), config);
	}

}
