package com.example.soundsynthesistest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private ToneGenerator mToneGenerator;
	private IntervalPlayer mIntervalPlayer;
	private RadioGroup mRadioGroup;
	private EditText mEditText;
	private EditText mEditText2;
	private EditText mIntervalEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mToneGenerator = new ToneGenerator();
		mIntervalPlayer = new IntervalPlayer(1);
		mEditText = (EditText) findViewById(R.id.freq_value);
		mEditText.setText("440");
		mEditText2 = (EditText) findViewById(R.id.base_note);
		mIntervalEditText = (EditText) findViewById(R.id.interval);
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
	}

	public void go(View v) {
		int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
		View radioButton = mRadioGroup.findViewById(radioButtonID);
		int wave_type = mRadioGroup.indexOfChild(radioButton);
		Log.v(TAG, "wave_type:" + wave_type);
		if (wave_type != -1) {
			mToneGenerator.setWaveType(wave_type);
		}
		Log.v(TAG, "EditText Value:" + mEditText.getText());
		mToneGenerator.playTone(
				Double.parseDouble(mEditText.getText().toString()), 1);
	}

	public void playInterval(View v) {


		Log.v(TAG, "EditText Value:" + mEditText2.getText());
		Log.v(TAG, "IntervalEditText Value:" + mIntervalEditText.getText());
		try {
			Note baseNote = Note.valueOf(mEditText2.getText().toString());
			int interval = Integer.parseInt(mIntervalEditText.getText()
					.toString());
			int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
			View radioButton = mRadioGroup.findViewById(radioButtonID);
			int wave_type = mRadioGroup.indexOfChild(radioButton);
			Log.v(TAG, "wave_type:" + wave_type);
			if (wave_type != -1) {
				mIntervalPlayer.setWaveType(wave_type);
			}			
			mIntervalPlayer.playInterval(baseNote, interval, 0);

		} catch (IllegalArgumentException e) {
			Log.d(TAG, "The base note user suggested does not exist!");
		}

	}

	/*
	 * private final int duration = 3; // in seconds private final int
	 * sampleRate = 44100; // 44100 sample rate private final int numSamples =
	 * duration * sampleRate; private final double sample[] = new
	 * double[numSamples]; private final double freqOfTone = 440; // in hz
	 * 
	 * private final byte generatedSnd[] = new byte[2 * numSamples];// why //
	 * 2*numSamples?
	 * 
	 * Handler handler = new Handler();
	 * 
	 * @Override protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.activity_main); }
	 * 
	 * public void go(View v) { // kick off sound on a different thread because
	 * it may take a while final Thread thread = new Thread(new Runnable() {
	 * public void run() { genTone(); //handler.post(new Runnable() { // public
	 * void run() { playSound(); // } //}); } }); thread.start();
	 * 
	 * }
	 * 
	 * @Override protected void onResume() { super.onResume();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * void genTone() { // fill out the array for (int i = 0; i < numSamples;
	 * ++i) { sample[i] = Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));
	 * }
	 * 
	 * // convert to 16bit PCM sound array // assume sample buffer is normalized
	 * int idx = 0; for (final double dVal : sample) { // scale to maximum
	 * amplitude final short val = (short) ((dVal * 32767)); // in 16-bit wav
	 * PCM, first byte is the low order byte generatedSnd[idx++] = (byte) (val &
	 * 0x00ff); generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8); } }
	 * 
	 * void playSound() { final AudioTrack audioTrack = new
	 * AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
	 * AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT,
	 * generatedSnd.length, AudioTrack.MODE_STATIC);
	 * audioTrack.write(generatedSnd, 0, generatedSnd.length);
	 * audioTrack.play(); }
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */

}
