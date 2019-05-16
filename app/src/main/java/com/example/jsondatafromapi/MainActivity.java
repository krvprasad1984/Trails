package com.example.jsondatafromapi;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declaring a text box control
    TextView txtJdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assignment of Logic Control txtJdata to UI Control txtDataBox
        txtJdata =(TextView) findViewById(R.id.txtDataBox);
    }

    //This inner class is for Async Task....
    private class getSOAPdata extends AsyncTask<String, Object, String> {

        //--- THIS HAS TO CHECK for its importance
        public static final String NAMESPACE = "https://diversityplus.com/";
        public static final String SOAP_ACTION  = "https://diversityplus.com/checkLogin";
        public static final String URL = "https://diversityplus.com/webservices/webservices.asmx";
        public static final String METHOD_NAME = "checkLogin";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            //Creating New SOAP Object
            SoapObject SOAPRequest = new SoapObject(NAMESPACE,METHOD_NAME);

            PropertyInfo SOAPProInf=new PropertyInfo();
            //Declaration of Parameters to for the SOAPRequest

            SOAPProInf.setName("infName");
            SOAPRequest.addProperty("username","krvprasad");
            SOAPRequest.addProperty("password","prasad1984");

            //Declaring SOAP Serialization Version
            SoapSerializationEnvelope SOAPEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            SOAPEnvelope.dotNet=true;
            SOAPEnvelope.setOutputSoapObject(SOAPRequest);

            String SOAPresp = "";
            try
            {
                HttpTransportSE SOAPTrans = new HttpTransportSE(URL);
                SOAPTrans.call(SOAP_ACTION,SOAPEnvelope);
                Object SOAPResult = (Object) SOAPEnvelope.getResponse();
                SOAPresp = SOAPResult.toString();

            }
            catch (Exception ex)
            {
                SOAPresp = "Error:"+ex.getMessage();
            }
            txtJdata.setText(SOAPresp);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
