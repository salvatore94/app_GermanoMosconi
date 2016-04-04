package org.polito.salvatore.germanomosconi;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MediaPlayer mp = new MediaPlayer();

    RecyclerView mRecycler;
    RecyclerView.Adapter mRAdapter;
    RecyclerView.LayoutManager mLayout;


    public void stopAndReset (MediaPlayer m) {
        m.stop();
        m.reset();
    }


    public void onButtonClick (View v) {
        stopAndReset(mp);
    }
    final String[] titoliPath = new String[] { "ah_non_lo_so_io", "avanti_e_n_drio", "avv_bisagno", "carte_co_la_cola", "chi_e_quel_mona", "chi_fa_quel_rumore_li",
            "come_se_ciama_elo_li","cos_e_caduto", "cosa_ghe_qua_sotto", "d_p", "dai_va_la", "dio_bono_de_dio", "dio_bubu",
            "dio_camaja_de_dio", "dio_cazzo", "dio_pa_pa_pa_pa", "dio_po_dio", "dio_porco__dio_cane", "dio_ss", "e_con_questo",
            "gabriele_sborina", "germano_e_il_telefono", "il_punteggio_dio_cane", "in_primo_piano", "la_societa", "ma_che_ooooh",
            "ma_e_possibile_che_sia_cosi_degli_imbecilli", "madonna_puttinaaaa", "madonna", "no_nessuno", "no_no_vai_in_mona", "non_e_possibile",
            "non_si_puo_scrivere_ste_notizie_in_maiuscolo", "orco_dio_in_serie", "passar_davanti", "pilota_romano_romano_andrea_de_cesaris", "porca_madonna",
            "porco_dio_1", "porco_dio_2", "portata_la_madonna", "se_non_bestemmio_guarda", "se_trovo_quello_che_mi_fa_innervosire",
            "se_venite_avanti_vi_do_un_pugno", "serie_esplosiva", "serrare_la_porta", "squadre", "stronzi", "tutto_da_capo",
            "vaffanculo_ti_e_tutti_quanti", "vaffanculo", "vai_in_mona", "vedo_tutto_meno_quello_che_dovrei_vedere"};


    final String[] titoli = new String[]  { "Ah non lo so io", "Avanti e n drio", "Avv bisagno", "Carte co la cola", "Chi e quel mona", "Chi fa quel rumore li",
            "Come se ciama elo li","Cos\'e caduto", "Cosa ghe qua sotto", "Dio porco", "Dai va la", "Dio bono de Dio", "Dio bubu",
            "Dio camaja de Dio", "Dio cazzo", "Dio pa pa pa pa", "Dio po Dio", "Dio porco  Dio cane", "Dio ss", "E con questo",
            "Gabriele Sborina", "Germano e il telefono", "Il punteggio Dio cane", "In primo piano", "La societa", "Ma che ooooh",
            "Ma e possibile che sia cosi degli imbecilli", "Madonna puttinaaaa", "Madonna", "No nessuno", "No no vai in mona", "Non e possibile",
            "Non si puo scrivere ste notizie in maiuscolo", "Orco Dio in serie", "Passar davanti", "Pilota romano romano Andrea DeCesaris", "Porca Madonna",
            "Porco Dio 1", "Porco Dio 2", "Portata la madonna", "Se non bestemmio guarda", "Se trovo quello che mi fa innervosire",
            "Se venite avanti vi do un pugno", "Serie esplosiva", "Serrare la porta", "Squadre", "Stronzi", "Tutto da capo",
            "Vaffanculo ti e tutti quanti", "Vaffanculo", "Vai in mona", "Vedo tutto meno quello che dovrei vedere" };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayout = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayout);

        mRAdapter = new MyAdapter(titoli);
        mRecycler.setAdapter(mRAdapter);

        ItemClickSupport.addTo(mRecycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                if (mp.isPlaying()) {
                    stopAndReset(mp);
                    Snackbar.make(v, "Riproduzione interrotta", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


                AssetFileDescriptor media = null;
                try {
                    media = getAssets().openFd(titoliPath[position] + ".mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.setDataSource(media.getFileDescriptor(), media.getStartOffset(), media.getLength());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.selectTrack(position);
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopAndReset(mp);
                    }
                });


                mp.start();

                Snackbar.make(v, titoli[position], Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //Log.d("List", "Ho cliccato sull'elemento con titolo " + mp.getDuration());
            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()) {
                    stopAndReset(mp);
                    Snackbar.make(view, "Riproduzione interrotta", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

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
            stopAndReset(mp);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //ritorna alla schermata urli
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this, BiographyActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, Nomi1Activity.class);
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(this, Nemici1Activity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
