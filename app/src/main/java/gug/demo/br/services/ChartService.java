package gug.demo.br.services;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.models.PontuacaoModel;

/**
 * Created by Éder on 01/03/2016.
 */
public class ChartService {

    public BarChart ExibeGraficoEquipes(ArrayList<PontuacaoModel> pontuacao, Context context, String description){
        BarChart chart = new BarChart(context);

        ArrayList<BarEntry> entries = GetEntries(pontuacao);

        ArrayList<String> labels = GetLabels(pontuacao);

        BarDataSet dataset = new BarDataSet(entries, "# Equipes");

        dataset.setColors(GetCorEquipes(pontuacao));

        BarData data = new BarData(labels, dataset);
        chart.setData(data);

        chart.setDescription(description);

        chart.animateY(2000);

        return chart;
    }

    private int[] GetCorEquipes(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<Integer> colors = new ArrayList<>();

        for (PontuacaoModel pontos: pontuacao) {

            TipoEquipe equipe = TipoEquipe.valueOf(pontos.Equipe);

            switch (equipe){
                case Vermelha:
                    colors.add(Color.RED);
                    break;
                case Verde:
                    colors.add(Color.GREEN);
                    break;
                case Preta:
                    colors.add(Color.BLACK);
                    break;
                case Branca:
                    colors.add(Color.WHITE);
                    break;
                case Amarela:
                    colors.add(Color.YELLOW);
                    break;
                case Azul:
                    colors.add(Color.BLUE);
                    break;
            }
        }

        int[] result = new int[colors.size()];
        for(int i = 0; i < colors.size(); i++){
            result[i] = colors.get(i);
        }

        return result;
    }

    private ArrayList<String> GetLabels(ArrayList<PontuacaoModel> pontuacao) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < pontuacao.size(); i++) {
//            result.add(pontuacao.get(i).Equipe);
            result.add("");
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
