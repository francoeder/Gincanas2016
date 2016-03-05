package gug.demo.br.gincanas2016;

import android.app.FragmentManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.github.mikephil.charting.charts.BarChart;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import java.util.ArrayList;

import gug.demo.br.dao.PontuacaoDao;
import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.enums.TipoProva;
import gug.demo.br.fragments.EquipesFragment;
import gug.demo.br.fragments.EstatisticaFragment;
import gug.demo.br.fragments.MainFragment;
import gug.demo.br.fragments.ProvasFragment;
import gug.demo.br.models.PontuacaoModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public static SQLiteDatabase database;

    //region Equipe Componentes
    NumberPicker nbPontos;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();

        // Inicializando Base de Dados
        InitiateDataBase();

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_main){
            fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
            toolbar.setSubtitle(R.string.principal_subtitle);
        } else if (id == R.id.nav_equipes) {
            fm.beginTransaction().replace(R.id.content_frame, new EquipesFragment()).commit();
            toolbar.setSubtitle(R.string.equipes_subtitle);
        } else if (id == R.id.nav_provas) {
            fm.beginTransaction().replace(R.id.content_frame, new ProvasFragment()).commit();
            toolbar.setSubtitle(R.string.provas_subtitle);
        } else if (id == R.id.nav_estatistica) {
            fm.beginTransaction().replace(R.id.content_frame, new EstatisticaFragment()).commit();
            toolbar.setSubtitle(R.string.estatistica_subtitle);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setContentView(BarChart chart){
        setContentView(chart);
    }

    //region Database Methods

    private void InitiateDataBase(){
        CreateDataBase();
        CreateTablePontuacao();
    }

    private void CreateDataBase(){
        database = openOrCreateDatabase("appgincanas", MODE_PRIVATE, null);
    }

    private void CreateTablePontuacao(){
        String sql = "create table if not exists Pontuacao(" +
                "Id integer primary key autoincrement, " +
                "Prova varchar(50), " +
                "Equipe varchar(50), " +
                "Pontos integer)";

        database.execSQL(sql);
    }

    //endregion

    //region Testes PontuacaoDao

    public void TesteAdicionaPontuacao(){
        PontuacaoDao PontuacaoDao = new PontuacaoDao();

//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.DesafioBiblico.toString(), TipoEquipe.Amarela.toString(), 15));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.Campo.toString(), TipoEquipe.Amarela.toString(), 20));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.Dardo.toString(), TipoEquipe.Amarela.toString(), 25));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.GarrafaDeAgua.toString(), TipoEquipe.Amarela.toString(), 10));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.Papelao.toString(), TipoEquipe.Amarela.toString(), 40));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.PiqueBandeira.toString(), TipoEquipe.Amarela.toString(), 35));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.QuemDisseIsso.toString(), TipoEquipe.Amarela.toString(), 5));

//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.DesafioBiblico.toString(), TipoEquipe.Azul.toString(), 15));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.Campo.toString(), TipoEquipe.Azul.toString(), 20));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.Dardo.toString(), TipoEquipe.Azul.toString(), 25));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.GarrafaDeAgua.toString(), TipoEquipe.Azul.toString(), 10));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.Papelao.toString(), TipoEquipe.Azul.toString(), 40));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.PiqueBandeira.toString(), TipoEquipe.Azul.toString(), 35));
//        PontuacaoDao.AdicionarPontuacao(new PontuacaoModel(TipoProva.QuemDisseIsso.toString(), TipoEquipe.Azul.toString(), 5));


//        PontuacaoModel equipeAmarela = PontuacaoDao.GetPontuacaoEquipe(TipoEquipe.Amarela);
//        PontuacaoModel equipeAzul = PontuacaoDao.GetPontuacaoEquipe(TipoEquipe.Azul);

        ArrayList<PontuacaoModel> Resultado = new ArrayList<PontuacaoModel>();

        Resultado = PontuacaoDao.GetPontuacaoProvasPorEquipe(TipoEquipe.Azul);
    }

    //endregion

}
