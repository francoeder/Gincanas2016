package gug.demo.br.models;

import gug.demo.br.enums.TipoProva;

/**
 * Created by Éder on 26/02/2016.
 */
public class PontuacaoModel {
    public String Prova;
    public String Equipe;
    public int Pontos;

    public PontuacaoModel(String prova, String equipe, int pontos) {
        Prova = prova;
        Pontos = pontos;
        Equipe = equipe;
    }
}
