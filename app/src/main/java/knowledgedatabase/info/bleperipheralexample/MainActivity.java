package knowledgedatabase.info.bleperipheralexample;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // サービスを開始するボタン
        Button serviceStartButton= (Button)this.findViewById(R.id.service_start);
        serviceStartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, knowledgedatabase.info.bleperipheralexample.BackendService.class);
                startService(intent);
            }
        });

        Button serviceStopButton = (Button)this.findViewById(R.id.service_stop);
        serviceStopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, knowledgedatabase.info.bleperipheralexample.BackendService.class);
                stopService(intent);
            }
        });
    }

    @SuppressLint("LongLogTag")
    private boolean isServiceRunnig(String className) {
        ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> listServiceInfo = am.getRunningServices(Integer.MAX_VALUE);

        Log.i(TAG + "isServiceRunnig", "Search Start: " + className);
        for (RunningServiceInfo curr : listServiceInfo) {
            Log.i(TAG + "isServiceRunnig", "Check: " + curr.service.getClassName());
            if (curr.service.getClassName().equals(className)) {
                Log.i(TAG + "isServiceRunnig", ">>>>>>FOUND!");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
