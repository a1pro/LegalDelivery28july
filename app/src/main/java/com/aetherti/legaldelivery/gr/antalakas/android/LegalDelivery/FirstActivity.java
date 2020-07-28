package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.aetherti.legaldelivery.Database.LDDatabaseAdapter;
import com.aetherti.legaldelivery.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE2 = 2;
    String serverFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        checkPermission();
        DeviceStatusRequest devicestatusrequest = new DeviceStatusRequest();
        devicestatusrequest.execute();
    }

    private void checkPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    @SuppressLint("StaticFieldLeak")
    public class DeviceStatusRequest extends AsyncTask<String, Integer, Boolean> {
        public LegalDeliveryActivity.AsyncResponse delegate = null;//Call back interface

        DeviceStatusRequest() {
            //	delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        HttpURLConnection conn = null;
        //private ProgressDialog pd;

        @SuppressWarnings("static-access")
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//			alreport.reportAudit("Downloading Task initiated!", LegalDeliveryActivity.this);

            Log.e("preExecute", "preExecute");
        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            String deviceID = Globals.GetDeviceID(getBaseContext(), getContentResolver());
            String url = "http://45.64.105.199/LDSWebService/api/Devices/GetDevice/" + deviceID;
            Log.e("device_id", url);
            Log.e("postExecute", "postExecute");
            try {
                HttpGet httppost = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                // StatusLine stat = response.getStatusLine();
                String responseStr = EntityUtils.toString(response.getEntity());
                ;
                JSONObject jsonObj = new JSONObject(responseStr);
                String s = jsonObj.getString("IsActive");
                serverFlag = s;
                if (s.equalsIgnoreCase("N")) {
                    terminate();
                    Intent i = new Intent(getApplicationContext(), BlueScreenDesign.class);
                    startActivityForResult(i, 0);
                } else if (s.equalsIgnoreCase("Y")) {
                    Intent i = new Intent(getApplicationContext(), LegalDeliveryActivity.class);
                    startActivityForResult(i, 0);
                    Log.e("IsActive", s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

        }

    }
    void terminate() {
        LDDatabaseAdapter dbHelper = new LDDatabaseAdapter(this);
        dbHelper.open();
        dbHelper.deleteLD();
        dbHelper.deleteHoliday();
        dbHelper.deleteRelatedP();
        dbHelper.deleteRepository();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
            case PERMISSION_REQUEST_CODE2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;
        }
    }


}
