package gug.demo.br.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.enums.TipoProva;
import gug.demo.br.gincanas2016.R;
import gug.demo.br.services.PontuacaoService;
import gug.demo.br.support.ItemData;
import gug.demo.br.support.SpinnerAdapter;
import gug.demo.br.support.Dialogs;

/**
 * Created by Éder on 25/02/2016.
 */
public class EquipesFragment extends Fragment {

    private PontuacaoService PontuacaoService;
    private Dialogs Dialogs;

    TipoEquipe EquipeVigente = TipoEquipe.Amarela;
    TipoProva ProvaVigente = TipoProva.Extra;

    TextView txt_prova;
    Map<TipoProva, String> mapTipoProva = new HashMap<TipoProva, String>();

    private View rootView;
    private FloatingActionButton btn_mais;
    private FloatingActionButton btn_menos;

    private NumberPicker nbp_pontos;

    private ArrayList<String> equipes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_equipes, container, false);

        PontuacaoService = new PontuacaoService();

        Dialogs = new Dialogs(getActivity());

        SetupProvas();

        SetupTxt_Prova();

        SetupButtons();

        SetupEquipes();

        return rootView;
    }

    private void SetupButtons() {
        btn_mais = (FloatingActionButton) rootView.findViewById(R.id.btn_mais);
        btn_menos = (FloatingActionButton) rootView.findViewById(R.id.btn_menos);
        nbp_pontos = (NumberPicker) rootView.findViewById(R.id.nbp_pontos);

        nbp_pontos.setMinValue(1);
        nbp_pontos.setMaxValue(50);

        btn_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, nbp_pontos.getValue());

                    String pontoOuPontos = (nbp_pontos.getValue() > 1) ? "pontos " : "ponto ";
                    String foiOuForam = (nbp_pontos.getValue() > 1) ? "foram " : "foi ";
                    String inseridoOuInseridos = (nbp_pontos.getValue() > 1) ? "inseridos " : "inserido ";
                    String qtdPontos = String.valueOf(nbp_pontos.getValue()) + " ";
                    String mensagemInsercao = qtdPontos + pontoOuPontos + foiOuForam + inseridoOuInseridos +
                            "para a equipe " + EquipeVigente.toString();

                    Dialogs.ShowAlert(mapTipoProva.get(ProvaVigente), mensagemInsercao);
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, -nbp_pontos.getValue());

                    String pontoOuPontos = (nbp_pontos.getValue() > 1) ? "pontos " : "ponto ";
                    String foiOuForam = (nbp_pontos.getValue() > 1) ? "foram " : "foi ";
                    String retiradoOuRetirados = (nbp_pontos.getValue() > 1) ? "retirados " : "retirado ";
                    String qtdPontos = String.valueOf(nbp_pontos.getValue()) + " ";
                    String mensagemRetirada = qtdPontos + pontoOuPontos + foiOuForam + retiradoOuRetirados +
                            "da equipe " + EquipeVigente.toString();

                    Dialogs.ShowAlert(mapTipoProva.get(ProvaVigente), mensagemRetirada);
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });


    }

    private void SetupEquipes() {

        ArrayList<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Equipe " + TipoEquipe.Amarela.toString(), R.mipmap.ic_flag_amarela));
        list.add(new ItemData("Equipe " + TipoEquipe.Azul.toString(), R.mipmap.ic_flag_azul));
        list.add(new ItemData("Equipe " + TipoEquipe.Branca.toString(), R.mipmap.ic_flag_branca));
        list.add(new ItemData("Equipe " + TipoEquipe.Preta.toString(), R.mipmap.ic_flag_preta));
        list.add(new ItemData("Equipe " + TipoEquipe.Verde.toString(), R.mipmap.ic_flag_verde));
        list.add(new ItemData("Equipe " + TipoEquipe.Vermelha.toString(), R.mipmap.ic_flag_vermelha));

        Spinner sp = (Spinner) rootView.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getActivity(),
                R.layout.spinner_item, R.id.txt, list);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void SetupProvas() {

        mapTipoProva.put(TipoProva.DesafioBiblico, "Desafio Bíblico");
        mapTipoProva.put(TipoProva.Campo, "Campo");
        mapTipoProva.put(TipoProva.GarrafaDeAgua, "Garrafa de Água");
        mapTipoProva.put(TipoProva.Papelao, "Papelão");
        mapTipoProva.put(TipoProva.Dardo, "Dardo");
        mapTipoProva.put(TipoProva.PiqueBandeira, "Pique-Bandeira");
        mapTipoProva.put(TipoProva.QuemDisseIsso, "Quem disse isso?");
        mapTipoProva.put(TipoProva.Extra, "Extra");

        txt_prova = (TextView) rootView.findViewById(R.id.txt_prova);
        txt_prova.setText("Prova: " + mapTipoProva.get(ProvaVigente));

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
        alertDialog.setTitle("Selecione a Prova que deseja pontuar:");
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

                        txt_prova.setText("Prova: " + mapTipoProva.get(ProvaVigente));
                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }

    public void SetupTxt_Prova() {
        txt_prova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupProvas();
            }
        });

    }
}