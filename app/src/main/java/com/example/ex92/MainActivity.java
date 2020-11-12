package com.example.ex92;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

/**
 * The type Main activity..
 *
 * @author Ori Ofek <oriofek106@gmail.com> 9/11/2020
 * @version 1.0
 * @since 9/11/2020
 * sort description:
 * take the data
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The S. if it aritmatic or geomatric
     */
    Switch s;
    /**
     * The Etd. take the d
     */
    EditText etd;
    /**
     * The Si. need to move
     */
    Intent si;
    /**
     * The Etx 1. get the first element
     */
    EditText etx1;

    /**
     * onCreate
     * Short description.
     * do on create
     * <p>
     *     Bundle savedInstanceState
     *
     * @param	savedInstanceState save the instance state
     * @return	none
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = (Switch) findViewById(R.id.sw);
        etx1 = (EditText)findViewById(R.id.etx1);
        etd = (EditText)findViewById(R.id.etd);
    }

    /**
     * Results
     *
     * Short description.
     * move to other activity
     * <p>
     *     View view
     *
     * @param	view - see which button pressed
     * @return	none
     */
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

    /**
     * check
     *
     * Short description.
     * do on create
     * <p>
     *
     *
     * @param
     * @return	true if it is an OK results else false
     */
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
