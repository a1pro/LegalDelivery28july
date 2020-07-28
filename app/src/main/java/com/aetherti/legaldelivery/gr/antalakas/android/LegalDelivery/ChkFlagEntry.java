package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.aetherti.legaldelivery.Database.LDDatabaseAdapter;
import com.aetherti.legaldelivery.R;
import com.aetherti.legaldelivery.Register.RegisterDeviceActivity;
import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Message.ServerStatusMessage;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import static java.net.HttpURLConnection.*;

public class ChkFlagEntry extends Activity {

    private static final String url = Globals.Domain + Globals.DomainPathDownloadServerStatus;
    String strMessageCount = "";
    AlertDialog alertDialog;
    String serverFlag;
    private static final int PERMISSION_REQUEST_CODE = 3;
    private static final int PERMISSION_REQUEST_CODE2 = 4;
    String deviceId;

    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
	/*	Intent i = new Intent(getApplicationContext(), LegalDeliveryActivity.class);
		startActivity(i);*/
       /* if (!Globals.IsNetworkAvailable(getBaseContext())) {
            alertDialog = new AlertDialog.Builder(ChkFlagEntry.this).create();
            alertDialog.setTitle("LegalDelivery 1.0.17");
            alertDialog.setMessage("Cannot connect to the service, network is not available. \nPlease check network settings.");
            alertDialog.setIcon(R.drawable.icon_alert);
            alertDialog.setButton("Setting",
                    new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int which) {
                            final Intent setting = new Intent(
                                    Settings.ACTION_WIFI_SETTINGS);
                            startActivity(setting);
                        }
                    });
            alertDialog.show();
        } else {*/
		/*DeviceStatusRequest devicestatusrequest = new DeviceStatusRequest();
		devicestatusrequest.execute();*/
			/*String serverFlag = chkServerFlag().toString();
			if (serverFlag.equalsIgnoreCase("y")) {
				Intent i = new Intent(this, LegalDeliveryActivity.class);
				startActivityForResult(i, 0);
			} else if (serverFlag.equalsIgnoreCase("n")) {
				terminate();
				Intent i = new Intent(this, BlueScreenDesign.class);
				startActivityForResult(i, 0);
			}*/
        // }
    }



	/*private boolean checkPermission1() {
		int result = ContextCompat.checkSelfPermission(ChkFlagEntry.this, Manifest.permission.READ_PHONE_STATE);
		return result == PackageManager.PERMISSION_GRANTED;
	}

	private void requestPermission() {

		if (ActivityCompat.shouldShowRequestPermissionRationale(ChkFlagEntry.this, Manifest.permission.READ_PHONE_STATE)) {
		} else {
			ActivityCompat.requestPermissions(ChkFlagEntry.this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
		}
	}*/

    private void checkPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            if (!Globals.IsNetworkAvailable(getBaseContext())) {
                                //////Changed By Sudheer...
                                /////////////////////////////////////////////////////////
                                ChkFlagEntry.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Globals.ShowNoNetworkMessage(getBaseContext());//May cause Double msg-Sudheer...
                                    }
                                });
                                return;
                            }
                            try {
                                DeviceStatusRequest devicestatusrequest = new DeviceStatusRequest();
                                devicestatusrequest.execute();
                            } catch (Exception e) {
                                Log.e("starting crash", e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private String chkServerFlag() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String deviceID = Globals.GetDeviceID(getBaseContext(), getContentResolver());
        String Server_Status = "";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        try {
            MultipartEntity entity = new MultipartEntity();
            entity.addPart("DeviceID", new StringBody(deviceID));
            httppost.setEntity(entity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                try {
                    String strDataLength = response.getFirstHeader("DataLength").getValue();
                    int dataLength = Integer.parseInt(strDataLength);
                    strMessageCount = response.getFirstHeader("MessageCount").getValue();

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    resEntity.writeTo(bos);

                    byte[] totalData = bos.toByteArray();
                    byte[] actualMessages = new byte[dataLength];

                    System.arraycopy(totalData, 0, actualMessages, 0, dataLength);

                    ServerStatusMessage ServerSM = ServerStatusMessage.parseFrom(actualMessages);
                    Server_Status = ServerSM.getServerStatus();

                } catch (Exception e) {
                    e.printStackTrace();
                    Server_Status = "Error while Cheching Server Status " + e.getMessage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Server_Status = "Error while Checking Server Status " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            Server_Status = "Error while Checking Server Status" + e.getMessage();
        }
        Log.i("Server Status:", Server_Status);//Delete after test: Sudheer..
        return Server_Status;
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

    void terminate() {
        LDDatabaseAdapter dbHelper = new LDDatabaseAdapter(this);
        dbHelper.open();
        dbHelper.deleteLD();
        dbHelper.deleteHoliday();
        dbHelper.deleteRelatedP();
        dbHelper.deleteRepository();
    }

    public class DeviceStatusRequest extends AsyncTask<String, Integer, Boolean> {
        public LegalDeliveryActivity.AsyncResponse delegate = null;//Call back interface
        HttpURLConnection conn = null;

        DeviceStatusRequest() {
            //	delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        //private ProgressDialog pd;

        @SuppressWarnings("static-access")
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//			alreport.reportAudit("Downloading Task initiated!", LegalDeliveryActivity.this);
			/*pd = new ProgressDialog(LegalDeliveryActivity.this);
			pd.setTitle("yjyjhgjhjhj Services...");
			pd.setMessage("It might hj few hj jhhjghjghghjhjhj download!");
			pd.setProgressStyle(pd.STYLE_HORIZONTAL);
			pd.setIndeterminate(true);
			pd.setCancelable(false);
			pd.show();
*/
            Log.e("preExecute", "preExecute");
        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            try {

                //String deviceID = "ffffffff-b789-3fb5-ffff-ffffe5fa3e73";
                	deviceId = Globals.GetDeviceID(getBaseContext(), getContentResolver());
                String url = "http://45.64.105.199/LDSWebService/api/Devices/GetDevice/" + deviceId;

                //String url = "http://45.64.105.199/LDSWebService/api/Devices/GetDevice/" + deviceId;
                HttpGet httppost = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                StatusLine stat = response.getStatusLine();
                int responseCode = stat.getStatusCode();

                //serverFlag = s;
                if (responseCode == 404) {
                    Intent i = new Intent(getApplicationContext(), RegisterDeviceActivity.class);
                    startActivity(i);
                    finishAffinity();
                } else {
                    String responseStr = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObj = new JSONObject(responseStr);
                    String s = jsonObj.getString("IsActive");
                    if (s.equalsIgnoreCase("N")) {
                        terminate();
                        Intent i = new Intent(getApplicationContext(), BlueScreenDesign.class);
                        startActivityForResult(i, 0);
                        finishAffinity();
                    } else if (s.equalsIgnoreCase("Y")) {
                        Intent i = new Intent(getApplicationContext(), LegalDeliveryActivity.class);
                        startActivity(i);
                    }
                }

            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
			/*if (pd.isIndeterminate()) {
				pd.setIndeterminate(false);
			}
			pd.setProgress(values[0]);*/
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            //	delegate.processFinish(result);
            //	pd.dismiss();

        }

    }
}
