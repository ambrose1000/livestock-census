package com.example.saidimu.livestock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class MainActivity extends AppCompatActivity {
    Button registration, ok;
    EditText email, pass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registration = (Button) findViewById(R.id.registration);
        ok = (Button) findViewById(R.id.ok);
        email = (EditText) findViewById(R.id.email);
        pass1 = (EditText) findViewById(R.id.pass1);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this, "Please Enter an Email", Toast.LENGTH_SHORT).show();
                }

               else if (pass1.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
                }


                else {

                    //bar.setVisibility(View.VISIBLE);
                    AsyncHttpClient c = new AsyncHttpClient();
                    RequestParams p = new RequestParams();
                    p.add("email", email.getText().toString());
                    p.add("pass1", pass1.getText().toString());
                    //p.add("pass2",pass2.getText().toString());
                    c.post("http://192.168.43.75 /blog/login.php", p, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                           // String x = new String(responseBody);
                            Toast.makeText(MainActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(getApplicationContext(), CensusActivity.class);
                            startActivity(s);
                            // bar.setVisibility(View.GONE);//hide progress
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                            //bar.setVisibility(View.GONE);//hide progress
                        }

                    });
                 }//end if
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(s);
            }
        });

    }

}

 