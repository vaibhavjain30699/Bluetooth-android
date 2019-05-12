package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter == null)
                    Toast.makeText(MainActivity.this,"Bluetooth not supported on this device!",Toast.LENGTH_LONG).show();
                else{
                    if(!bluetoothAdapter.isEnabled()){
                        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableIntent,1);
                    }

                    //Toast.makeText(MainActivity.this,bluetoothAdapter.getAddress(),Toast.LENGTH_LONG).show();

                    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                    ArrayList list  = new ArrayList();

                    if(pairedDevices.size()>0){
                        for(BluetoothDevice device : pairedDevices){
                            String devicename = device.getName();
                            String deviceAdd = device.getAddress();
                            list.add("Name: " + devicename+"\nMAC Address: "+deviceAdd);
                        }
                        listView = (ListView)findViewById(R.id.list_item);
                        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(arrayAdapter);
                    }

                }
            }
        });


    }
}
