package gug.demo.br.dao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.enums.TipoProva;
import gug.demo.br.gincanas2016.MainActivity;
import gug.demo.br.models.PontuacaoModel;

/**
 * Created by Éder on 26/02/2016.
 */
public class PontuacaoDao extends Activity {

    public void AdicionarPontuacao(PontuacaoModel pontuacao) {
        String sql =
                "insert into Pontuacao(Prova, Equipe, Pontos)" +
                        "Values(" +
                        "'" + pontuacao.Prova + "', " +
                        "'" + pontuacao.Equipe + "', " +
                        pontuacao.Pontos + ")";

        MainActivity.database.execSQL(sql);
    }

    public ArrayList<PontuacaoModel> GetPontuacaoTodasEquipes() {
        ArrayList<PontuacaoModel> result = new ArrayList<PontuacaoModel>();

        for(TipoEquipe equipe: TipoEquipe.values()){
            result.add(GetPontuacaoGeralEquipe(equipe));
        }

        return result;
    }

    public ArrayList<PontuacaoModel> GetPontuacaoTodasEquipesPorProva(TipoProva prova) {
        ArrayList<PontuacaoModel> result = new ArrayList<PontuacaoModel>();

        for (TipoEquipe equipe: TipoEquipe.values()){
            result.add(GetPontuacaoEquipePorProva(equipe, prova));
        }

        return result;
    }

    public PontuacaoModel GetPontuacaoGeralEquipe(TipoEquipe equipe) {
        PontuacaoModel result;

        String sql =
                "select SUM(Pontos) as Pontos from Pontuacao " +
                        "where Equipe = '" + equipe.toString() + "'";

        Cursor cursor = MainActivity.database.rawQuery(sql, null);

        int pontos = cursor.getColumnIndex("Pontos");

        cursor.moveToFirst();
        do {
            String thisProva = "Todas";
            String thisPontos = (cursor.getString(pontos) != null) ? cursor.getString(pontos) : "0";
            result = new PontuacaoModel(thisProva, equipe.toString(), Integer.valueOf(thisPontos));
        } while (cursor.moveToNext());

        return result;
    }

    public ArrayList<PontuacaoModel> GetPontuacaoProvasPorEquipe(TipoEquipe equipe) {
        ArrayList<PontuacaoModel> result = new ArrayList<PontuacaoModel>();

        String sql =
                "select Prova, SUM(Pontos) as Pontos, Prova from Pontuacao " +
                        "where Equipe = '" + equipe.toString() + "'" +
                        " group by Prova";

        Cursor cursor = MainActivity.database.rawQuery(sql, null);

        int prova = cursor.getColumnIndex("Prova");
        int pontos = cursor.getColumnIndex("Pontos");

        cursor.moveToFirst();
        do {
            String thisProva = cursor.getString(prova);
            String thisPontos = (cursor.getString(pontos) != null) ? cursor.getString(pontos) : "0";
            result.add(new PontuacaoModel(thisProva, equipe.toString(), Integer.valueOf(thisPontos)));
        } while (cursor.moveToNext());

        return result;
    }

    public ArrayList<PontuacaoModel> GetPontuacaoExtras() {
        ArrayList<PontuacaoModel> result = new ArrayList<PontuacaoModel>();

        for (TipoEquipe equipe: TipoEquipe.values()){
            result.add(GetPontuacaoEquipePorProva(equipe, TipoProva.Extra));
        }

        return result;
    }

    private PontuacaoModel GetPontuacaoEquipePorProva (TipoEquipe equipe, TipoProva prova){
        PontuacaoModel result;

        String sql =
                "select SUM(Pontos) as Pontos from Pontuacao " +
                        "where Equipe = '" + equipe.toString() + "' " +
                        "and Prova = '" + prova.toString() + "' ";

        Cursor cursor = MainActivity.database.rawQuery(sql, null);

        int pontos = cursor.getColumnIndex("Pontos");

        cursor.moveToFirst();
        do {
            String thisProva = prova.toString();
            String thisPontos = (cursor.getString(pontos) != null) ? cursor.getString(pontos) : "0";
            result = new PontuacaoModel(thisProva, equipe.toString(), Integer.valueOf(thisPontos));
        } while (cursor.moveToNext());

        return result;
    }
}
