package com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Download;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aetherti.legaldelivery.Database.LDDatabaseAdapter;
import com.aetherti.legaldelivery.R;
import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.BlueScreenDesign;
import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.Globals;
import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.ErrorReporting.AuditLogReporter;
import com.aetherti.legaldelivery.gr.antalakas.android.LegalDelivery.LegalDeliveryActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class DownloadControlFragment extends Fragment implements OnClickListener {

    private String download_btn_type;
    DownloadControlFragment mcontext = this;
    android.content.SharedPreferences sharedPrefManager;
    TextView numOfRecordsTextView;
    TextView fromDateTextView;
    TextView toDateTextView;
    Calendar cal = Calendar.getInstance();
    int count_records;
    int count_uploading_records;
    String Message;
    View V;
    AuditLogReporter alreport;
    String _strDTFrom = "";
    String _strDTTo = "";
    private String serverFlag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alreport = new AuditLogReporter();
        alreport.reportAudit("Getting Data From database:", getActivity());
        LDDatabaseAdapter LDDb = new LDDatabaseAdapter(getActivity());
        LDDb.open();
        count_records = 9;
        count_uploading_records = 9;
        LDDb.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        V = inflater.inflate(R.layout.ld_download_control_fragment, container, false);
        final Button downloadButton = (Button) V.findViewById(R.id.downloadButton);
        final Button downloadFilteredButton = (Button) V.findViewById(R.id.downloadFilteredButton);
        final Button selectAllButton = (Button) V.findViewById(R.id.selectAllButton);
        final Button clearAllButton = (Button) V.findViewById(R.id.clearAllButton);
        final Button saveButton = (Button) V.findViewById(R.id.saveButton);


        fromDateTextView = (TextView) V.findViewById(R.id.fromDateTextView);
        fromDateTextView.setText("[Not Set]");

        toDateTextView = (TextView) V.findViewById(R.id.toDateTextView);
        toDateTextView.setText("[Not Set]");

        fromDateTextView.setOnClickListener(this);
        toDateTextView.setOnClickListener(this);

        numOfRecordsTextView = (TextView) V.findViewById(R.id.numOfRecords);
        final LinearLayout mainlayout = (LinearLayout) V.findViewById(R.id.blue);

        /////////////////////////////////////////////////////////////////////
        final Context con = getActivity();
        /////////////////////////////////////////////////////////////////////

        downloadButton.setOnClickListener(new View.OnClickListener() {
                                              public void onClick(View v) {
                                                  DeviceStatusRequest devicestatusrequest = new DeviceStatusRequest("d");
                                                  devicestatusrequest.execute();
                                                  alreport.reportAudit("Download Button touched!", con);

                                              }
                                          });

                downloadFilteredButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        DeviceStatusRequest devicestatusrequest = new DeviceStatusRequest("df");
                        devicestatusrequest.execute();

                    }
                });
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DownloadListFragment downloadList = (DownloadListFragment) getFragmentManager()
                        .findFragmentById(R.id.DownloadListFragmentID);
                downloadList.SelectAll();
                TextView text = (TextView) V.findViewById(R.id.fromDateTextView);
                text.setText("[Not Set]");
                TextView text1 = (TextView) V.findViewById(R.id.toDateTextView);
                text1.setText("[Not Set]");
            }
        });

        clearAllButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DownloadListFragment downloadList = (DownloadListFragment) getFragmentManager()
                        .findFragmentById(R.id.DownloadListFragmentID);
                downloadList.ClearAll();

                TextView text = (TextView) V.findViewById(R.id.fromDateTextView);
                text.setText("[Not Set]");
                TextView text1 = (TextView) V.findViewById(R.id.toDateTextView);
                text1.setText("[Not Set]");

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    DownloadListFragment downloadList = (DownloadListFragment) getFragmentManager().findFragmentById(R.id.DownloadListFragmentID);
                    //downloadList.Save();
                    downloadList.SaveWithResults(_strDTFrom, _strDTTo);
                    ((LDDownloadActivity) getActivity()).isSaved = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return V;
    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            Toast.makeText(getActivity(), "back press is disabled",
                    Toast.LENGTH_LONG).show();
        // Disable back button..............
    }


    public class DeviceStatusRequest extends AsyncTask<String, Integer, Boolean> {
        public LegalDeliveryActivity.AsyncResponse delegate = null;//Call back interface

        DeviceStatusRequest(String df) {
            download_btn_type=df;
            //	delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }

        HttpURLConnection conn = null;
        //private ProgressDialog pd;

        @SuppressWarnings("static-access")
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("preExecute", "preExecute");
        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            try {
                //	String deviceID = "ffffffff-a789-3fb5-ffff-ffffe5fa3e73";
                String deviceID = Globals.GetDeviceID(getActivity().getBaseContext(), getActivity().getContentResolver());
                String url = "http://45.64.105.199/LDSWebService/api/Devices/GetDevice/" + deviceID;

                //String url = "http://45.64.105.199/LDSWebService/api/Devices/GetDevice/" + deviceId;
                HttpGet httppost = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                // StatusLine stat = response.getStatusLine();
                String responseStr = EntityUtils.toString(response.getEntity());
                JSONObject jsonObj = new JSONObject(responseStr);
                serverFlag = jsonObj.getString("IsActive");
            } catch (Exception e) {
                e.printStackTrace();
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
            if (serverFlag.equalsIgnoreCase("N")) {
                terminate();
                Intent i = new Intent(getActivity(), BlueScreenDesign.class);
                startActivityForResult(i, 0);
                getActivity().finishAffinity();
            } else if (serverFlag.equalsIgnoreCase("Y")) {
                if(download_btn_type.equalsIgnoreCase("d")){
                    if (count_uploading_records == 0 && count_records > 0)
                        show_exception_download_alert("No previous record updated");
                    else if (count_uploading_records < count_records & count_uploading_records != 0)
                        show_exception_download_alert(count_uploading_records + " records are Pending for upload");
                    else {
                        show_exception_download_alert("Really want to download?");
                    }
                }else {
                    DownloadListFragment downloadList = (DownloadListFragment) getFragmentManager()
                            .findFragmentById(R.id.DownloadListFragmentID);

                    String strDTFrom = fromDateTextView.getText().toString();
                    String strDTTo = toDateTextView.getText().toString();
                    _strDTFrom = strDTFrom;
                    _strDTTo = strDTTo;
                    // Try catch implementation with if else for date validation
                    // date 7/8/2012
                    try {
                        if (strDTFrom == "[Not Set]") {
                            Toast.makeText(getActivity(), "Please Select Start Date.", Toast.LENGTH_SHORT).show();
                        } else if (strDTTo == "[Not Set]") {
                            Toast.makeText(getActivity(), "Please Select End Date.", Toast.LENGTH_SHORT).show();
                        } else {
                            String[] fromDateSplit = strDTFrom.split("-");
                            strDTFrom = fromDateSplit[2] + "-" + fromDateSplit[0] + "-" + fromDateSplit[1];
                            String[] toDateSplit = strDTTo.split("-");
                            strDTTo = toDateSplit[2] + "-" + toDateSplit[0] + "-" + toDateSplit[1];
						/*downloadList.DownloadFiltered(dtFrom.getfromdate(),
								dtTo.gettodate(), dtTo.getMonthOfYear(),
								dtTo.getYear());
						*/
                            if (count_uploading_records == 0 && count_records > 0)
                                show_exception_filtered_download_alert("No previous record updated", downloadList, strDTFrom, strDTTo);
                            else if (count_uploading_records < count_records & count_uploading_records != 0)
                                show_exception_filtered_download_alert(count_uploading_records + " records are Pending for upload", downloadList, strDTFrom, strDTTo);
                            else {
                                show_exception_filtered_download_alert("Really want to download?", downloadList, strDTFrom, strDTTo);
                                //downloadList = (DownloadListFragment) getFragmentManager().findFragmentById(R.id.DownloadListFragmentID);
                                //downloadList.Download();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // TODO: handle exception
                    }
                }

            }
            //	delegate.processFinish(result);
            //	pd.dismiss();

        }

    }

    void terminate() {
        LDDatabaseAdapter dbHelper = new LDDatabaseAdapter(getActivity());
        dbHelper.open();
        dbHelper.deleteLD();
        dbHelper.deleteHoliday();
        dbHelper.deleteRelatedP();
        dbHelper.deleteRepository();
    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fromDateTextView:
                HandleDateAttempt(v);
                break;

            case R.id.toDateTextView:
                HandleDateAttempt(v);
                break;
        }
    }

    private void show_exception_filtered_download_alert(String msg, final DownloadListFragment pDownloadFragment, final String strDTFrom, final String strDtTo) {
        AlertDialog.Builder alert_for_download = new AlertDialog.Builder(getActivity());
        alert_for_download.setIcon(R.drawable.ic_action_warning);
        alert_for_download.setTitle("Confirmation");
        alert_for_download.setMessage(msg + ", new records will be overwritten! ");
        alert_for_download.setCancelable(false);
        alert_for_download.setPositiveButton("Continue Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alreport.reportAudit(" Force Continuing Download!", getActivity());
                pDownloadFragment.DownloadFiltered2(strDTFrom, strDtTo);
            }
        });
        alert_for_download.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertdownload = alert_for_download.create();
        alertdownload.show();
    }

    private void show_exception_download_alert(String msg) {
        AlertDialog.Builder alert_for_download = new AlertDialog.Builder(getActivity());
        alert_for_download.setIcon(R.drawable.ic_action_warning);
        alert_for_download.setTitle("Confirmation");
        alert_for_download.setMessage(msg + ", new records will be overwritten! ");
        alert_for_download.setCancelable(false);
        alert_for_download.setPositiveButton("Continue Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alreport.reportAudit(" Force Continuing Download!", getActivity());
                DownloadListFragment downloadList = (DownloadListFragment) getFragmentManager().findFragmentById(R.id.DownloadListFragmentID);
                downloadList.Download();
            }
        });
        alert_for_download.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertdownload = alert_for_download.create();
        alertdownload.show();
    }

    @SuppressWarnings("deprecation")
    private void HandleDateAttempt(View v) {
        TextView tv = (((LDDownloadActivity) getActivity()).lastDateView) = (TextView) v;

        String strDate = "";
        if (tv.getText().toString().equals("[Not Set]"))
            strDate = String.format("%02d-%02d-%04d",
                    ((LDDownloadActivity) getActivity()).mMonth,
                    ((LDDownloadActivity) getActivity()).mDay,
                    ((LDDownloadActivity) getActivity()).mYear);
        else
            strDate = tv.getText().toString();

        try {
            cal.setTime(Globals.sdf.parse(strDate + " 0:00 AM"));
            (((LDDownloadActivity) getActivity()).mYear) = cal
                    .get(Calendar.YEAR);
            (((LDDownloadActivity) getActivity()).mMonth) = cal
                    .get(Calendar.MONTH);
            (((LDDownloadActivity) getActivity()).mDay) = cal
                    .get(Calendar.DAY_OF_MONTH);
            getActivity().showDialog(Globals.DATE_DIALOG_ID);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void SetNumOfDownloadMessages(String strMessageCount) {
        numOfRecordsTextView.setText(strMessageCount);
    }
}
