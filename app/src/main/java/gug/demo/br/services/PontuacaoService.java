package gug.demo.br.services;

import java.util.ArrayList;

import gug.demo.br.dao.PontuacaoDao;
import gug.demo.br.enums.TipoEquipe;
import gug.demo.br.enums.TipoProva;
import gug.demo.br.models.PontuacaoModel;


/**
 * Created by Éder on 01/03/2016.
 */
public class PontuacaoService {

    PontuacaoDao PontuacaoDao;
    ChartService ChartService;

    public PontuacaoService() {
        PontuacaoDao = new PontuacaoDao();
        ChartService = new ChartService();
    }

    public void AdicionarPontuacao(TipoProva prova, TipoEquipe equipe, int pontos){
        PontuacaoModel pontuacao = new PontuacaoModel(prova.toString(), equipe.toString(), pontos);
        PontuacaoDao.AdicionarPontuacao(pontuacao);
    }

    public ArrayList<PontuacaoModel> GetPontuacaoTodasEquipes(){
        ArrayList<PontuacaoModel> result = new ArrayList<PontuacaoModel>();
        result = PontuacaoDao.GetPontuacaoTodasEquipes();

        return result;
    }
}
