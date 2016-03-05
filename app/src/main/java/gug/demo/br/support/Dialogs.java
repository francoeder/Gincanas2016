package gug.demo.br.support;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by Éder on 01/03/2016.
 */
public class Dialogs extends Activity {

    private final int duration = Toast.LENGTH_SHORT;
    private Context Context;

    private AlertDialog alertDialog;

    public Dialogs(Context context) {
        Context = context;
    }

    public void ShowToastMessage(CharSequence message) {
        Toast toast = Toast.makeText(Context, message, duration);
        toast.show();
    }

    public void ShowAlert (String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(Context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }





}
