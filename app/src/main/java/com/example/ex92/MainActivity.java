package com.example.ex92;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch s;
    EditText etd;
    Intent si;
    EditText etx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = (Switch) findViewById(R.id.sw);
        etx1 = (EditText)findViewById(R.id.etx1);
        etd = (EditText)findViewById(R.id.etd);
    }

    public void results(View view) {
        if(check())
        {
            si = new Intent(this,results.class);
            si.putExtra("type",s.isChecked());
            si.putExtra("x1",etd.getText().toString());
            si.putExtra("n",etx1.getText().toString());
            startActivity(si);
        }

    }

    private boolean check() {
        boolean flag = true;
        if(etd.getText().toString().equals("") || etd.getText().toString().equals("-") || etd.getText().toString().equals(".")|| etd.getText().toString().equals("-."))
        {
            flag = false;
            Toast.makeText(this, "there is no d",
                    Toast.LENGTH_LONG).show();
        }
        if(etx1.getText().toString().equals("") || etx1.getText().toString().equals("-") || etx1.getText().toString().equals(".")|| etx1.getText().toString().equals("-."))
        {
            flag = false;
            Toast.makeText(this, "there is no x1",
                    Toast.LENGTH_LONG).show();
        }
        return flag;
    }
}
