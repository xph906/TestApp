package com.example.xpan.singlewindowapp;

import 	android.app.AlertDialog;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    CheckBox cb, cb2, dcb;
    Button btn;
    EditText et, et2, et3;
    final Integer viewIDConstant = R.id.button1;
    Integer viewIDObj;
    static Integer viewIDStatic;
    Integer viewIDRet;

    Button btn1, btn2, btn3, btn4;
    Integer[] arr = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
    //  Integer[] arr = new Integer[4];
    List<Integer> al, ll;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        cb = (CheckBox) findViewById(R.id.checkbox_allow);
        cb2 = (CheckBox) findViewById(R.id.checkbox_allow2);
        et = (EditText) findViewById(R.id.edittext_password);
        et2 = (EditText) findViewById(R.id.edittext_password2);
        et3 = (EditText) findViewById(R.id.edittext_password3);
        btn = (Button) findViewById(R.id.button_start);
        dcb = new CheckBox(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        sendCredentialToInternet(new A(cb));
        //testEventMethod();
        testDialog();
        final int id = 101010101;
        FrameLayout frame = new FrameLayout(this);
        frame.setId(R.id.fl);
        setContentView(frame, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        if (savedInstanceState == null) {
            Fragment newFragment = new FireMissilesDialogFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fl, newFragment).commit();
            ((FireMissilesDialogFragment)newFragment).onCreateDialog(null);
        }
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

        Button b = new Button(this);
        b.setText("aaa");
        testEventMethod();
        dcb.setText("Dyanmic checkbox");
        if(dcb.isChecked())
            sendCredentialInfo(imei);

    }

    private void testDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.title_name)
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean use1 = System.currentTimeMillis() % 4 == 0;
                        if (cb2.isChecked())
                            sendCredentialInfo(et2.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.setTitle("abc Title");
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
        editor.putBoolean("DIALOG", dialog.toString().endsWith("ABC"));

        editor.putBoolean("TESTDYNAMIC", dcb.isChecked());

    }

    private void sendCredentialToInternet(A abc) {
        final A finalA = abc;
//        final boolean xyz1 = xxyz1;

        //sendCredentialInfo(et.getText().toString());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendCredentialInfo(et.getText().toString());

                finalA.getCheckBox().setText("WAHAHA Texts!!!");
                if (finalA.getCheckBox().isChecked()) {
                    boolean noUsePredicate = System.currentTimeMillis() % 2 == 0;
                    if (noUsePredicate == false) System.out.println("ABC");

                    boolean use2 = System.currentTimeMillis() % 3 == 0;
                    if (use2)
                        et3 = null;
                    boolean use1 = System.currentTimeMillis() % 4 == 0;
                    if (use1)
                        sendCredentialInfo(et3.getText().toString());
                }
            }
        });
        btn.setText("Some texts for field button");
        et.setText(R.string.title_name);
    }

    private void testEventMethod() {
        Button btn = (Button) findViewById(R.id.button4);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        btn.setOnKeyListener(new View.OnKeyListener() {
             @Override
             public boolean onKey(View view, int i, KeyEvent keyEvent) {
                 return false;
             }
         }
        );
        btn.setText("Some texts for local button");
        btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                System.out.println("ABCDE");
                return ;
            }
        });
    }


    private Integer getID(Integer id) {
        System.out.println("id");
        return id % 2 == 0 ? 0 : id;
    }

    private void sendCredentialInfo(String secret) {
        //String cnt = "ABCD";
        //if(cb.isChecked()){
        RequestChangePasswordTask tast = new RequestChangePasswordTask();
        try {
            tast.postData(secret);
        } catch (Exception e) {

        }
        //}

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.xpan.singlewindowapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.xpan.singlewindowapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }



}
