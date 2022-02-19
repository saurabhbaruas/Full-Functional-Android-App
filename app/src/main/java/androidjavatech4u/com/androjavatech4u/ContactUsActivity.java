package androidjavatech4u.com.androjavatech4u;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ContactUsActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        toolbar = (Toolbar) findViewById(R.id.toolbarcontact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");
        TextView txt1 = (TextView) findViewById(R.id.txt1);
        TextView txt2 = (TextView) findViewById(R.id.txt2);
        TextView txt3 = (TextView) findViewById(R.id.txt3);
        TextView txt4 = (TextView) findViewById(R.id.txt4);
        TextView txt5 = (TextView) findViewById(R.id.txt5);
        TextView txt6 = (TextView) findViewById(R.id.txt6);
        TextView txt7 = (TextView) findViewById(R.id.txt7);
        TextView txt8 = (TextView) findViewById(R.id.txt8);
        TextView txt9 = (TextView) findViewById(R.id.txt9);
        TextView txt10 = (TextView) findViewById(R.id.txt10);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


//        Typeface typeFace = Typeface.createFromAsset(getAssets(), "HelveticaNeue.ttf");
//        txt1.setTypeface(typeFace);
//        txt2.setTypeface(typeFace);
//        txt3.setTypeface(typeFace);
//        txt4.setTypeface(typeFace);
//        txt5.setTypeface(typeFace);
//        txt6.setTypeface(typeFace);
//        txt7.setTypeface(typeFace);
//        txt8.setTypeface(typeFace);
//        txt9.setTypeface(typeFace);
//        txt10.setTypeface(typeFace);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void web(View view) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://androjava-tech4u.business.site")));
    }
}

