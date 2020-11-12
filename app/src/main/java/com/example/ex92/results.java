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
 * The type Results.
 *
 * @author Ori Ofek <oriofek106@gmail.com> 9/11/2020
 * @version 1.0
 * @since 9/11/2020
 * short  description
 * show the results
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
     * The Arr. the arr of the elements
     */
    String[] arr;
    /**
     * The X 1. the first element
     */
    float x1;
    /**
     * The i. need to save the position
     */
    int i;
    /**
     * The D. the distance between the numbers
     */
    float d;
    /**
     * The Type of the seq
     */
    boolean type; // 0 geo 1 math
    /**
     * The List view that used to show the elements
     */
    ListView ls;


     /**
      * onCreate
      * Short description.
      * do one create
      * <p>
      *     Bundle savedInstanceState
      *
      * @param	 savedInstanceState save the Intance state (:
      * @return	none.
      */
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

    /**
     * editNumbers
     * Short description.
     * design the number and remove the .0
     * <p>
     *     float number
     *
     * @param number - is the number that the function get and return it as string
     * @return	return a string that a human can understand the number.
     */
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

    /**
     * onItemClick
     * Short description.
     * onItemClick listener use for the list view
     * <p>
     *     AdapterView<?> adapterView
     *     View view
     *     int pos
     *     long id
     *
     * @param adapterView - the object,view - the item that selected ,pos - the position in the list view,id - the id of the element
     * @return	none
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        i = pos;
        view.setOnCreateContextMenuListener(this);
    }

    /**
     * onCreateContextMenu
     * Short description.
     * onCreateContextMenu listener use for the ContextMenu
     * <p>
     *     ContextMenu menu
     *     View v
     *     ContextMenu.ContextMenuInfo menuInfo
     *
     * @param  menu - the object,v - the item that selected ,menuInfo - the info
     * @return	none
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("options");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
    }

    /**
     * onContextItemSelected
     * Short description.
     * onContextItemSelected listener use for the ContextMenu
     * <p>
     *     MenuItem item
     *
     * @param  item - the item that selected
     * @return	true if it worked
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String oper = item.getTitle().toString();
        if (oper.equals("Place")) {
            result.setText("Place: "+editNumbers((i+1)));
        }
        if (oper.equals("take summery")) {

            result.setText("summery: "+editNumbers(sn[i]));
        }
        return true;
    }

    /**
     * Finish.
     * finish the activity and return to the old one
     *
     * <p>
     *     view the view
     * </p>
     * @param view - which button selected
     */
    public void finish (View view){
            finish();
    }

}