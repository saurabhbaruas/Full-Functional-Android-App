       package androidjavatech4u.com.androjavatech4u;
        import android.content.Context;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.ads.AdListener;
        import com.google.android.gms.ads.AdRequest;
        import com.google.android.gms.ads.AdView;
        import com.google.android.gms.ads.InterstitialAd;

        import java.util.Timer;
        import java.util.TimerTask;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private boolean exit = false;
    private WebView webView;
    TextView tt;
    AdView adView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        webView = (WebView) findViewById(R.id.webview);
        tt= (TextView)findViewById(R.id.text1);

        checkConnection();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }



    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void checkConnection(){
        if(isOnline()){
            //    Toast.makeText(MainActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();

            webView.loadUrl("https://www.youtube.com/AndroJavaTech4U");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            adView=(AdView)findViewById(R.id.adView);

     //webView.onPause();
            // webView.pauseTimers();
            AdRequest adRequest=new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            adView.loadAd(adRequest);

            interstitialAd=new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                   // startActivity(new Intent(MainActivity.this,ContactUsActivity.class));
                    finish();
                    interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }
            });

        }else{
            Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
            tt.setText("         Can't Connect to the Server.\n Plese Check Your Internet Connection");
        }
    }










    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(webView.canGoBack()) {
                webView.goBack();
            }
            else
            {
                //super.onBackPressed();
                if(exit)
                {
                    next();
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Tap Back button again to exit", Toast.LENGTH_SHORT).show();

                }
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        exit=false;
                    }
                },2000);

                exit=true;
            }
        }}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Email) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"suryaassigen@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(Intent.EXTRA_TEXT, "mail body");
            startActivity(Intent.createChooser(intent, ""));

        }

        if (id == R.id.action_Contact) {

            String phone = "+919310683500";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);

        }
//        if (id == R.id.action_WhatsApp) {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.whatsapp.com/")));
//
//
//        }



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_facebook)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AndroJavaTech4U/")));

        }
        else if (id == R.id.nav_twitter) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/AndroJavaTech4U")));


        }
        else if (id == R.id.nav_google) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/+AndroJavaTech4U")));


        }
        else if (id == R.id.nav_linkedin) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/androjavatech4u/")));
        }




        else if (id == R.id.drawer_item_merchandise) {

          //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCqkYdg0BCQLJ1p3Hn9vjATw")));

        }
        else if (id == R.id.drawer_ic_menu_call) {
            startActivity(new Intent(MainActivity.this,ContactUsActivity.class));
            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SanghpriyTengar/")));

        }
        else if (id == R.id.ic_menu_camera) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=NR7SttYtJi0&t=6s")));

        }


        else if (id == R.id.nav_amozon) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/")));

        }

        else if (id == R.id.nav_flipkart) {

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flipkart.com/")));

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void next()
    {
        if(interstitialAd.isLoaded())
        {
            interstitialAd.show();
        }
        else
        {
            //    startActivity(new Intent(MainActivity.this,Next.class));
            // startActivity(new Intent(MainActivity.this,ContactUsActivity.class));
            finish();
        }}








}
