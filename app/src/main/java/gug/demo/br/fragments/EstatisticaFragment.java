package gug.demo.br.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.enums.TipoProva;
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

    Map<TipoProva, String> mapTipoProva = new HashMap<TipoProva, String>();
    TipoProva ProvaVigente = TipoProva.Extra;
    TipoEquipe EquipeVigente = TipoEquipe.Amarela;

    FloatingActionButton btn_view_final_result;
    FloatingActionButton btn_view_result_por_prova;
    FloatingActionButton btn_view_result_por_equipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;

        rootView = inflater.inflate(R.layout.fragment_estatistica, container, false);

        PontuacaoService = new PontuacaoService();
        ChartService = new ChartService(getActivity());

        SetupButtons();

        return rootView;
    }

    private void SetupButtons() {
        btn_view_final_result = (FloatingActionButton) rootView.findViewById(R.id.btn_view_final_result);
        btn_view_result_por_prova = (FloatingActionButton) rootView.findViewById(R.id.btn_view_result_por_prova);
        btn_view_result_por_equipe = (FloatingActionButton) rootView.findViewById(R.id.btn_view_result_por_equipe);

        btn_view_final_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PontuacaoModel> pontuacao = PontuacaoService.GetPontuacaoTodasEquipes();
                BarChart chart = ChartService.ExibeGraficoEquipes(pontuacao, getActivity(), "Resultado Geral da Gincana");
                FrameLayout frame_layout_estatistica = (FrameLayout) rootView.findViewById(R.id.frame_layout_estatistica);
                frame_layout_estatistica.addView(chart);
            }
        });

        btn_view_result_por_prova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogProvas();
            }
        });

        btn_view_result_por_equipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEquipes();
            }
        });

    }

    private void DialogEquipes(){

        String[] equipes = {
                TipoEquipe.Amarela.toString(),
                TipoEquipe.Azul.toString(),
                TipoEquipe.Branca.toString(),
                TipoEquipe.Preta.toString(),
                TipoEquipe.Verde.toString(),
                TipoEquipe.Vermelha.toString()};

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_prova_item, equipes);

        final Spinner sp = new Spinner(getActivity());
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);

        sp.setVisibility(View.VISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Selecione a Equipe para visualizar os pontos:");
        alertDialog.setView(sp);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int position = sp.getSelectedItemPosition();

                        switch (position) {
                            case 0:
                                EquipeVigente = TipoEquipe.Amarela;
                                break;
                            case 1:
                                EquipeVigente = TipoEquipe.Azul;
                                break;
                            case 2:
                                EquipeVigente = TipoEquipe.Branca;
                                break;
                            case 3:
                                EquipeVigente = TipoEquipe.Preta;
                                break;
                            case 4:
                                EquipeVigente = TipoEquipe.Verde;
                                break;
                            case 5:
                                EquipeVigente = TipoEquipe.Vermelha;
                                break;
                        }

                        ArrayList<PontuacaoModel> pontuacao = PontuacaoService.GetPontuacaoProvasPorEquipe(EquipeVigente);
                        BarChart chart = ChartService.ExibeGraficoProvas(pontuacao, getActivity(), "Resultados da Equipe " + EquipeVigente.toString());
                        FrameLayout frame_layout_estatistica = (FrameLayout) rootView.findViewById(R.id.frame_layout_estatistica);
                        frame_layout_estatistica.addView(chart);

                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }

    private void DialogProvas() {

        mapTipoProva.put(TipoProva.DesafioBiblico, "Desafio Bíblico");
        mapTipoProva.put(TipoProva.Campo, "Campo");
        mapTipoProva.put(TipoProva.GarrafaDeAgua, "Garrafa de Água");
        mapTipoProva.put(TipoProva.Papelao, "Papelão");
        mapTipoProva.put(TipoProva.Dardo, "Dardo");
        mapTipoProva.put(TipoProva.PiqueBandeira, "Pique-Bandeira");
        mapTipoProva.put(TipoProva.QuemDisseIsso, "Quem disse isso?");
        mapTipoProva.put(TipoProva.Extra, "Extra");

        String[] provas = {
                mapTipoProva.get(TipoProva.DesafioBiblico),
                mapTipoProva.get(TipoProva.Campo),
                mapTipoProva.get(TipoProva.GarrafaDeAgua),
                mapTipoProva.get(TipoProva.Papelao),
                mapTipoProva.get(TipoProva.Dardo),
                mapTipoProva.get(TipoProva.PiqueBandeira),
                mapTipoProva.get(TipoProva.QuemDisseIsso),
                mapTipoProva.get(TipoProva.Extra)};

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_prova_item, provas);

        final Spinner sp = new Spinner(getActivity());
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);

        sp.setVisibility(View.VISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Selecione a Prova para visualizar os pontos:");
        alertDialog.setView(sp);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int position = sp.getSelectedItemPosition();

                        switch (position) {
                            case 0:
                                ProvaVigente = TipoProva.DesafioBiblico;
                                break;
                            case 1:
                                ProvaVigente = TipoProva.Campo;
                                break;
                            case 2:
                                ProvaVigente = TipoProva.GarrafaDeAgua;
                                break;
                            case 3:
                                ProvaVigente = TipoProva.Papelao;
                                break;
                            case 4:
                                ProvaVigente = TipoProva.Dardo;
                                break;
                            case 5:
                                ProvaVigente = TipoProva.PiqueBandeira;
                                break;
                            case 6:
                                ProvaVigente = TipoProva.QuemDisseIsso;
                                break;
                            case 7:
                                ProvaVigente = TipoProva.Extra;
                                break;
                        }

                        ArrayList<PontuacaoModel> pontuacao = PontuacaoService.GetPontuacaoTodasEquipesPorProva(ProvaVigente);
                        BarChart chart = ChartService.ExibeGraficoEquipes(pontuacao, getActivity(), "Resultado da Prova " + mapTipoProva.get(ProvaVigente));
                        FrameLayout frame_layout_estatistica = (FrameLayout) rootView.findViewById(R.id.frame_layout_estatistica);
                        frame_layout_estatistica.addView(chart);

                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }
}
