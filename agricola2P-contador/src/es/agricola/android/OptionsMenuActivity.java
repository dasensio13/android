package es.agricola.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class OptionsMenuActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case R.id.creditos:
			dialog = buildDialogo(R.string.detalle_creditos, R.string.creditos);
			break;
		case R.id.instrucciones:
			dialog = buildDialogo(R.string.detalle_instrucciones, R.string.instrucciones);
			break;
		default:
			dialog = null;
		}
		return dialog;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.creditos:
		case R.id.instrucciones:
			showDialog(item.getItemId());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Dialog buildDialogo(int idTexto, int idTitulo) {
		Context mContext = getApplicationContext();
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.info, null);

		TextView helpText = (TextView) layout.findViewById(R.id.TextView_HelpText);
        helpText.setText(getResources().getString(idTexto));

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(layout)
				.setTitle(idTitulo)
				.setCancelable(true)
				.setPositiveButton(R.string.aceptar,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						});
		return builder.create();
	}
}