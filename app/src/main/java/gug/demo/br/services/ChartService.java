package gug.demo.br.services;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.enums.TipoProva;
import gug.demo.br.gincanas2016.R;
import gug.demo.br.models.PontuacaoModel;

/**
 * Created by Éder on 01/03/2016.
 */
public class ChartService {

    Context context;

    Map<TipoProva, String> mapTipoProva = new HashMap<TipoProva, String>();

    public ChartService(Context context) {
        mapTipoProva.put(TipoProva.DesafioBiblico, "Desafio Bíblico");
        mapTipoProva.put(TipoProva.Campo, "Campo");
        mapTipoProva.put(TipoProva.GarrafaDeAgua, "Garrafa de Água");
        mapTipoProva.put(TipoProva.Papelao, "Papelão");
        mapTipoProva.put(TipoProva.Dardo, "Dardo");
        mapTipoProva.put(TipoProva.PiqueBandeira, "Pique-Bandeira");
        mapTipoProva.put(TipoProva.QuemDisseIsso, "Quem disse isso?");
        mapTipoProva.put(TipoProva.Extra, "Extra");
    }

    public BarChart ExibeGraficoEquipes(ArrayList<PontuacaoModel> pontuacao, Context context, String description) {
        BarChart chart = new BarChart(context);

        ArrayList<BarEntry> entries = GetEntries(pontuacao);

        ArrayList<String> labels = GetLabelsEquipes(pontuacao);

        BarDataSet dataset = new BarDataSet(entries, "# Equipes");

        dataset.setColors(GetCorEquipes(pontuacao));

        BarData data = new BarData(labels, dataset);
        chart.setData(data);

        chart.setDescription(description);

        chart.animateY(2000);

        return chart;
    }

    public BarChart ExibeGraficoProvas(ArrayList<PontuacaoModel> pontuacao, Context context, String description) {
        BarChart chart = new BarChart(context);

        ArrayList<BarEntry> entries = GetEntries(pontuacao);

        ArrayList<String> labels = GetLabelsProvas(pontuacao);

        BarDataSet dataset = new BarDataSet(entries, "# Provas");

        dataset.setColors(GetCorProvas(pontuacao));

        BarData data = new BarData(labels, dataset);
        chart.setData(data);

        chart.setDescription(description);

        chart.animateY(2000);

        return chart;
    }

    private int[] GetCorEquipes(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<Integer> colors = new ArrayList<>();

        for (PontuacaoModel pontos : pontuacao) {

            TipoEquipe equipe = TipoEquipe.valueOf(pontos.Equipe);

            switch (equipe) {
                case Vermelha:
                    colors.add(Color.RED);
//                    colors.add(context.getResources().getColor(R.color.corVermelha));
                    break;
                case Verde:
                    colors.add(Color.GREEN);
//                    colors.add(context.getResources().getColor(R.color.corVerde));
                    break;
                case Preta:
                    colors.add(Color.BLACK);
//                    colors.add(context.getResources().getColor(R.color.corPreta));
                    break;
                case Branca:
                    colors.add(Color.WHITE);
//                    colors.add(context.getResources().getColor(R.color.corBranca));
                    break;
                case Amarela:
                    colors.add(Color.YELLOW);
//                    colors.add(context.getResources().getColor(R.color.corAmarela));
                    break;
                case Azul:
                    colors.add(Color.BLUE);
//                    colors.add(context.getResources().getColor(R.color.corAzul));
                    break;
            }
        }

        int[] result = new int[colors.size()];
        for (int i = 0; i < colors.size(); i++) {
            result[i] = colors.get(i);
        }

        return result;
    }

    private int[] GetCorProvas(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<Integer> colors = new ArrayList<>();

        for (PontuacaoModel pontos : pontuacao) {

            TipoProva prova = TipoProva.valueOf(pontos.Prova);

//            DesafioBiblico = 0xFF004157;
//            Campo          = 0xFF005A78;
//            GarrafaDeAgua  = 0xFF006D91;
//            Papelao        = 0xFF0086B3;
//            Dardo          = 0xFF0099CC;
//            PiqueBandeira  = 0xFF00A8E0;
//            QuemDisseIsso  = 0xFF00B6F2;
//            Extra          = 0xFF4AD2FF;

            switch (prova) {
                case DesafioBiblico:
//                    colors.add(0xFF004157);
                    colors.add(Color.BLUE);
                    break;
                case Campo:
//                    colors.add(0xFF005A78);
                    colors.add(Color.BLUE);
                    break;
                case GarrafaDeAgua:
//                    colors.add(0xFF006D91);
                    colors.add(Color.BLUE);
                    break;
                case Papelao:
//                    colors.add(0xFF0086B3);
                    colors.add(Color.BLUE);
                    break;
                case Dardo:
//                    colors.add(0xFF0099CC);
                    colors.add(Color.BLUE);
                    break;
                case PiqueBandeira:
//                    colors.add(0xFF00A8E0);
                    colors.add(Color.BLUE);
                    break;
                case QuemDisseIsso:
//                    colors.add(0xFF00B6F2);
                    colors.add(Color.BLUE);
                    break;
                case Extra:
//                    colors.add(0xFF4AD2FF);
                    colors.add(Color.BLUE);
                    break;
            }
        }

        int[] result = new int[colors.size()];
        for (int i = 0; i < colors.size(); i++) {
            result[i] = colors.get(i);
        }

        return result;
    }

    private ArrayList<String> GetLabelsEquipes(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < pontuacao.size(); i++) {
//            result.add(pontuacao.get(i).Equipe);
            result.add("");
        }

        return result;
    }

    private ArrayList<String> GetLabelsProvas(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < pontuacao.size(); i++) {
            result.add(mapTipoProva.get(pontuacao.get(i).Prova));
        }

        return result;
    }

    private ArrayList<BarEntry> GetEntries(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<BarEntry> result = new ArrayList<>();

        for (int i = 0; i < pontuacao.size(); i++) {
            result.add(new BarEntry(pontuacao.get(i).Pontos, i));
        }

        return result;
    }
}
