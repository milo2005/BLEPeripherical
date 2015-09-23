package com.unicauca.ubicuas.bleperipheral;

import android.app.Activity;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    static final ParcelUuid Service_UUID = ParcelUuid.fromString("0000b81d-0000-1000-8000-00805f9b34fb");

    TextView txtTemp, txtState;
    SeekBar temp;
    Switch state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTemp = (TextView) findViewById(R.id.txt_temp);
        txtState = (TextView) findViewById(R.id.txt_state);

        state = (Switch) findViewById(R.id.state);
        state.setOnClickListener(this);

        temp = (SeekBar) findViewById(R.id.temp);
        temp.setOnSeekBarChangeListener(this);

        txtTemp.setText(""+ temp.getProgress()+"°");
    }


    //region Snackbar Listener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        txtTemp.setText(""+progress+"°");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    //endregion

    //region Switch Listener
    @Override
    public void onClick(View v) {
        if(state.isChecked()) {

        }else {

        }
    }
    //endregion
}
