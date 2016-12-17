package com.example.xpan.singlewindowapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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
    CheckBox cb;
    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb = (CheckBox)findViewById(R.id.checkbox_allow);
        btn = (Button)findViewById(R.id.button_start);
        et = (EditText)findViewById(R.id.edittext_password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendCredentialInfo(et.getText().toString());
                sendCredentialInfo(this.toString());
            }
        });
    }

    private void sendCredentialInfo(String secret){
        String cnt = "ABCD";
        if(cb.isChecked()){
            RequestChangePasswordTask tast = new RequestChangePasswordTask();
            try{
                tast.postData(secret);
            }catch(Exception e){

            }
        }

    }

    class RequestChangePasswordTask extends AsyncTask< String, String, String > {
        String protocol = "http";
        String serverip = "garuda.cs.northwestern.edu";
        String serverport = "3000";
        @Override
        protected String doInBackground(String...params) {

            try {
                postData(params[0]);
            } catch (Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Double result) {

        }
        protected void onProgressUpdate(Integer...progress) {

        }

        /*
        The function that makes an HTTP Post to the server endpoint that handles the
        change password operation.
        */
        public void postData(String valueIWantToSend) throws Exception {
//a
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(protocol + serverip + ":" + serverport + "/changepassword");
            List< NameValuePair > nameValuePairs = new ArrayList< NameValuePair >(2);
            nameValuePairs.add(new BasicNameValuePair("username", "name"));
            nameValuePairs.add(new BasicNameValuePair("newpassword", et.getText().toString()));
            HttpResponse responseBody;
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Check if the password is complex enough
            responseBody = httpclient.execute(httppost);
            InputStream in = responseBody.getEntity().getContent();
            String result = convertStreamToString( in );
            result = result.replace("\n", "");

        }


        private String convertStreamToString(InputStream in ) throws IOException {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader( in , "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            } in .close();
            return sb.toString();
        }

    }


}
