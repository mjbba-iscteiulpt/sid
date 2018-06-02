package inducesmile.com.sid.App;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import inducesmile.com.sid.Helper.UserLogin;
import inducesmile.com.sid.R;

//Esta aplicação serve como base para vos ajudar, precisam de completar os métodos To do de modo a que a aplicação faça o minimo que é suposto, podem adicionar novas features ou mudar a UI se acharem relevante.
public class LoginActivity extends AppCompatActivity {
    private EditText ip, port, username, password;
    private Button login;
    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        String ipText = myPrefs.getString("ip", "");
        String portText = myPrefs.getString("port", "");

        TextView labelIp = (TextView) findViewById(R.id.ip);
        labelIp.setText(ipText);
        TextView labelPort = (TextView) findViewById(R.id.port);
        labelPort.setText(portText);

        ip = findViewById(R.id.ip);
        port = findViewById(R.id.port);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
    }

    public void loginClick(View v) {
        URL url;
        String response = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            url = new URL("http://192.168.1.76/testConnection.php?user="+username.getText().toString()+"&pass="+password.getText().toString());
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                response = line;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("login", response);
        if(response.contains("Connected")) {
            myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.putString("ip", ip.getText().toString());
            editor.putString("port", port.getText().toString());
            editor.apply();

            new UserLogin(ip.getText().toString(), port.getText().toString(), username.getText().toString(), password.getText().toString());
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }


}
