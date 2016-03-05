package gug.demo.br.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;

import java.lang.reflect.Array;
import java.util.ArrayList;

import gug.demo.br.gincanas2016.MainActivity;
import gug.demo.br.gincanas2016.R;
import gug.demo.br.models.PontuacaoModel;
import gug.demo.br.services.ChartService;
import gug.demo.br.services.PontuacaoService;

/**
 * Created by Éder on 25/02/2016.
 */
public class EstatisticaFragment extends Fragment {

    LayoutInflater inflater;
    ViewGroup container;

    View rootView;

    private PontuacaoService PontuacaoService;
    private ChartService ChartService;

    FloatingActionButton btn_view_final_result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;

        rootView = inflater.inflate(R.layout.fragment_estatistica, container, false);

        PontuacaoService = new PontuacaoService();
        ChartService = new ChartService();

        SetupButtons();

        return  rootView;
    }

    private void SetupButtons(){
        btn_view_final_result = (FloatingActionButton) rootView.findViewById(R.id.btn_view_final_result);

        btn_view_final_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PontuacaoModel> pontuacao = PontuacaoService.GetPontuacaoTodasEquipes();
                BarChart chart = ChartService.ExibeGraficoEquipes(pontuacao, getActivity(), "# Pontuação das Equipes - GUG 2016");
                FrameLayout frame_layout_estatistica = (FrameLayout) rootView.findViewById(R.id.frame_layout_estatistica);
                frame_layout_estatistica.addView(chart);
            }
        });
    }
}
