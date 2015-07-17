package com.example.andrea.ramcleaner;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class RamCleaner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_kill_and_clean);
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        for (int i = 0; i < procInfos.size(); i++) {
            //if (procInfos.get(i).processName.equals("com.android.music")) {
            //Toast.makeText(null, "music is running",
            //      Toast.LENGTH_LONG).show();
            activityManager.killBackgroundProcesses(procInfos.get(i).processName);
        }
        String result1 = "";
        try {
            result1 = com.example.andrea.ramcleaner.Shell.sudo("sync");
            result1 = com.example.andrea.ramcleaner.Shell.sudo("echo 3 > /proc/sys/vm/drop_caches");
        } catch (com.example.andrea.ramcleaner.Shell.ShellException e) {
            e.printStackTrace();
        }
/*        Toast mioToast = Toast.makeText(RamCleaner.this,
                "RAM and Drop Cache cleaned.",
                Toast.LENGTH_LONG);
        mioToast.show();
        //Toast.makeText(getApplicationContext(), "RAM and Drop Cache cleaned.",
          //      Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 */
        System.exit(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.kill_and_clean, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
