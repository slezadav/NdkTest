package test.seznam.cz.ndktest;

//import android.support.v7.app.ActionBarActivity;
// This line is not needed as we are not targetting older devices

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    static{
        System.loadLibrary("main");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText(stringFromJNI("Ahoj"));

        for(int i=10000;i<1000000000;i*=10){
            long start=System.currentTimeMillis();
            execTest(i);
            Log.i("TAG","Java time for "+i+"  :  "+(System.currentTimeMillis()-start));
            start=System.currentTimeMillis();
            test(i);
            Log.i("TAG","Cpp time for "+i+"  :  "+(System.currentTimeMillis()-start));
        }

    }

    private void execTest(int limit){
        int j=0;
        for(int i=0;i<limit;++i){
            j=i/100;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public native String  stringFromJNI(String param);
    public native void  test(int limit);
}