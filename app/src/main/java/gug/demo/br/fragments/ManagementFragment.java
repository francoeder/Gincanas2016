package gug.demo.br.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gug.demo.br.gincanas2016.R;
import gug.demo.br.services.PontuacaoService;
import gug.demo.br.support.Dialogs;

/**
 * Created by Éder on 25/02/2016.
 */
public class ManagementFragment extends Fragment {

    View rootView;

    private PontuacaoService PontuacaoService;

    private Dialogs Dialogs;

    private Button btn_reset_data;

    private boolean choice = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_management, container, false);

        PontuacaoService = new PontuacaoService();
        Dialogs = new Dialogs(getActivity());

        SetupButtons();

        return rootView;
    }

    private void SetupButtons() {
        btn_reset_data = (Button) rootView.findViewById(R.id.btn_reset_data);

        btn_reset_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmAlert("Tem certeza?", "Não haverá mais volta hein!?");
            }
        });
    }

    public void ConfirmAlert (String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Eu quero Resetar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        PontuacaoService.ResetData();
                        Dialogs.ShowAlert("Agora já era!", "É cara você apagou tudo!");
                    }
                })
                .setNegativeButton("Para tudo!", null)
                .show();
    }
}
