package com.unicauca.ubicuas.bleperipheral;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    static final ParcelUuid SERVICE_UUID = ParcelUuid.fromString("0000b81d-0000-1000-8000-00805f9b34fb");
    static final UUID DESCRIPTOR_UUID = UUID.fromString("0000b82d-0000-1000-8000-00805f9b34fb");
    static final UUID CHARACTERISTIC_UUID = UUID.fromString("0000b83d-0000-1000-8000-00805f9b34fb");

    static final int REQUEST_BLUETOOTH=100;

    TextView txtTemp, txtState;
    SeekBar temp;
    Switch state;

    BluetoothManager manager;
    BluetoothAdapter adapter;
    BluetoothLeAdvertiser advertiser;

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

        txtTemp.setText("" + temp.getProgress() + "°");

        manager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();
        advertiser = adapter.getBluetoothLeAdvertiser();

        enableBluetooth();
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
    //endregion1

    //region Bluetooth Enable
    public void enableBluetooth(){
        if(adapter!=null && !adapter.isEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,REQUEST_BLUETOOTH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_BLUETOOTH){
            if(resultCode == RESULT_OK){
                //Habilito el Bluetooth
            }
        }
    }
    //endregion

    public void startAdvertasing(){

        AdvertiseSettings settings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setConnectable(true)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_LOW)
                .build();

        String temperature = ""+temp.getProgress();

        AdvertiseData data = new AdvertiseData.Builder()
                .addServiceData(SERVICE_UUID, temperature.getBytes())
                .build();

        advertiser.startAdvertising();
    }

    public void stopAdvertasing(){

    }
}
