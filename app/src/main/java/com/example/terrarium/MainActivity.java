package com.example.terrarium;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    int bt_res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onMyButtonClick(View view)
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(100);
        }

        if (view.getId() == R.id.rel1) {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.rel2) {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.rel3) {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        }

    }







    //                if (bt_res == 0) {
//                    new HttpTask().execute("http://192.168.50.160/L");
//                    bt_res = 1;
//                    button.setText("on");
//                } else {
//                    new HttpTask().execute("http://192.168.50.160/H");
//                    bt_res = 0;
//                    button.setText("off");
//                }
//
//            }
//        });
//    }
//
    private class HttpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }
}

