package com.example.ex92;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * activity Results.
 */
public class results extends AppCompatActivity implements View.OnCreateContextMenuListener ,AdapterView.OnItemClickListener {
    /**
     * show The Result.
     */
    TextView result;
    /**
     * The Gi. the intent
     */
    Intent gi;
    /**
     * The Sn. sum of the elemnts in the arr
     */
    float[] sn;
    /**
     * The Arr.
     */
    String[] arr;
    /**
     * The X 1.
     */
    float x1;
    /**
     * The .
     */
    int i;
    /**
     * The D.
     */
    float d;
    /**
     * The Type.
     */
    boolean type; // 0 geo 1 math
    /**
     * The Ls.
     */
    ListView ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        arr = new String[20];
        sn = new float[20];
        ls = (ListView)findViewById(R.id.ls);
        result = (TextView)findViewById(R.id.results);
        gi = getIntent();
        x1 = Float.valueOf(gi.getStringExtra("x1"));
        d = Float.valueOf(gi.getStringExtra("n"));
        type = gi.getBooleanExtra("type",true);

        if(type)
        {
            for (int i = 1; i <= 20; i++)
            {
                arr[i-1] = editNumbers(x1 * (float)Math.pow((double)d,(double)(i-1)));
                if(i == 1)
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]);
                }
                else
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]) + (sn[i-2]);
                }
            }
        }
        else
        {
            for (int i = 1; i <= 20; i++)
            {
                arr[i-1] = editNumbers(x1 + (i-1)*d);
                if(i == 1)
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]);
                }
                else
                {
                    sn[i-1] = Float.parseFloat(arr[i-1]) + (sn[i-2]);
                }

            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this
                ,R.layout.support_simple_spinner_dropdown_item,arr);
        ls.setAdapter(adp);

        ls.setOnItemClickListener(this);
    }

    private String editNumbers(float number)
    {
        String newNum = "";
        newNum = String.valueOf(number);

        while (!newNum.equals("") && Float.valueOf(newNum.substring(1)) == number)
        {
            newNum = newNum.substring(1);
        }

        if((((float)((int)number)) == (float)number))
        {
            return String.valueOf((int)number);
        }
        return newNum;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long is) {
        i = pos;
        view.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("options");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String oper = item.getTitle().toString();
        if (oper.equals("Place")) {
            result.setText("Place: "+(i+1));
        }
        if (oper.equals("take summery")) {

            result.setText("summery: "+sn[i]);
        }
        return true;
    }

    /**
     * Finish.
     *
     * @param view the view
     */
    public void finish (View view){
            finish();
    }

}