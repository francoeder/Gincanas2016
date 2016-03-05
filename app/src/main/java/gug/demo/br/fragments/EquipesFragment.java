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
    private FloatingActionButton btn_mais_um;
    private FloatingActionButton btn_mais_cinco;
    private FloatingActionButton btn_mais_dez;
    private FloatingActionButton btn_mais_quinze;
    private FloatingActionButton btn_mais_trinta;
    private FloatingActionButton btn_menos_um;
    private FloatingActionButton btn_menos_cinco;
    private FloatingActionButton btn_menos_dez;
    private FloatingActionButton btn_menos_quinze;
    private FloatingActionButton btn_menos_trinta;

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
        btn_mais_um = (FloatingActionButton) rootView.findViewById(R.id.btn_mais_um);
        btn_mais_cinco = (FloatingActionButton) rootView.findViewById(R.id.btn_mais_cinco);
        btn_mais_dez = (FloatingActionButton) rootView.findViewById(R.id.btn_mais_dez);
        btn_mais_quinze = (FloatingActionButton) rootView.findViewById(R.id.btn_mais_quinze);
        btn_mais_trinta = (FloatingActionButton) rootView.findViewById(R.id.btn_mais_trinta);
        btn_menos_um = (FloatingActionButton) rootView.findViewById(R.id.btn_menos_um);
        btn_menos_cinco = (FloatingActionButton) rootView.findViewById(R.id.btn_menos_cinco);
        btn_menos_dez = (FloatingActionButton) rootView.findViewById(R.id.btn_menos_dez);
        btn_menos_quinze = (FloatingActionButton) rootView.findViewById(R.id.btn_menos_quinze);
        btn_menos_trinta = (FloatingActionButton) rootView.findViewById(R.id.btn_menos_trinta);

        btn_mais_um.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, 1);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "1 ponto foi inserido para a equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_mais_cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, 5);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "5 pontos foram inseridos para a equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_mais_dez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, 10);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "10 pontos foram inseridos para a equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_mais_quinze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, 15);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "15 pontos foram inseridos para a equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_mais_trinta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, 30);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "30 pontos foram inseridos para a equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_menos_um.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, -1);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "1 ponto foi retirado da equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_menos_cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, -5);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "5 pontos foram retirados da equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_menos_dez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, -10);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "10 pontos foram retirados da equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_menos_quinze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, -15);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "15 pontos foram retirados da equipe " + EquipeVigente.toString());
                } catch (Exception ex) {
                    Log.i("Erro", "Aconteceu o seguinte erro: " + ex);
                }
            }
        });

        btn_menos_trinta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PontuacaoService.AdicionarPontuacao(ProvaVigente, EquipeVigente, -30);
                    Dialogs.ShowAlert(ProvaVigente.toString(), "30 pontos foram retirados da equipe " + EquipeVigente.toString());
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