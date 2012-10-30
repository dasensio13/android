package es.agricola.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ContadorActivity extends Activity {

	private static final String TAG = "ContadorActivity";

	private static final String ID_NOMBRE_JUGADOR1 = "NOMBRE_JUGADOR_1";
	private static final String ID_NOMBRE_JUGADOR2 = "NOMBRE_JUGADOR_2";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setNombres();
    }

	/* calcular */
	public void calcular(final View view) {
		calcularTotalJugador1();
		calcularTotalJugador2();
		calcularGanador();
	}

	private void calcularTotalJugador1() {
		calcularExtraOvejas(R.id.j1extraovejas, R.id.j1ovejas);
		calcularExtraCerdos(R.id.j1extracerdos, R.id.j1cerdos);
		calcularExtraVacas(R.id.j1extravacas, R.id.j1vacas);
		calcularExtraCaballos(R.id.j1extracaballos, R.id.j1caballos);
		int[] subtotales = {R.id.j1ovejas,R.id.j1cerdos,R.id.j1vacas,R.id.j1caballos,R.id.j1extraovejas,
				R.id.j1extracerdos,R.id.j1extravacas,R.id.j1extracaballos,R.id.j1ampliaciones,R.id.j1edificios};
		calcularTotalJugador(R.id.j1total, subtotales);
	}

	private void calcularTotalJugador2() {
		calcularExtraOvejas(R.id.j2extraovejas, R.id.j2ovejas);
		calcularExtraCerdos(R.id.j2extracerdos, R.id.j2cerdos);
		calcularExtraVacas(R.id.j2extravacas, R.id.j2vacas);
		calcularExtraCaballos(R.id.j2extracaballos, R.id.j2caballos);
		int[] subtotales = {R.id.j2ovejas,R.id.j2cerdos,R.id.j2vacas,R.id.j2caballos,R.id.j2extraovejas,
				R.id.j2extracerdos,R.id.j2extravacas,R.id.j2extracaballos,R.id.j2ampliaciones,R.id.j2edificios};
		calcularTotalJugador(R.id.j2total, subtotales);
	}

	private void calcularTotalJugador(int idTotal, int[] ids) {
		TextView totalView = (TextView)findViewById(idTotal);
		int total = sumar(ids);
		totalView.setText(String.valueOf(total));
	}

	private int sumar(int[] ids) {
		Integer total = 0;
		for (int i=0; i<ids.length; i++) {
			total = total + getIntegerValueOf(ids[i]);
		}
		return total;
	}

	private Integer getIntegerValueOf(int id) {
		View valor = findViewById(id);
		if (valor instanceof EditText) {
			return getIntegerValueOfEdit(id);
		} else if (valor instanceof TextView) {
			return getIntegerValueOfText(id);
		} else {
			Log.d(TAG, "Vista de tipo desconocido");
			return Integer.valueOf(0);
		}
	}

	private void calcularExtraOvejas(int idExtra, int idNum) {
		TextView totalView = (TextView)findViewById(idExtra);
		int num = getIntegerValueOfEdit(idNum);
		int total = 0;
		if (num<=3) {
			total = -3;
		} else if (num>=8 && num<=10) {
			total = 1;
		} else if (num==11 || num==12) {
			total = 2;
		} else if (num>=13) {
			total = num-10;
		}
		totalView.setText(String.valueOf(total));
	}

	private void calcularExtraCerdos(int idExtra, int idNum) {
		TextView totalView = (TextView)findViewById(idExtra);
		int num = getIntegerValueOfEdit(idNum);
		int total = 0;
		if (num<=3) {
			total = -3;
		} else if (num==7 || num==8) {
			total = 1;
		} else if (num==9 || num==10) {
			total = 2;
		} else if (num>=11) {
			total = num-8;
		}
		totalView.setText(String.valueOf(total));
	}

	private void calcularExtraVacas(int idExtra, int idNum) {
		TextView totalView = (TextView)findViewById(idExtra);
		int num = getIntegerValueOfEdit(idNum);
		int total = 0;
		if (num<=3) {
			total = -3;
		} else if (num==6 || num==7) {
			total = 1;
		} else if (num==8 || num==9) {
			total = 2;
		} else if (num>=10) {
			total = num-7;
		}
		totalView.setText(String.valueOf(total));
	}

	private void calcularExtraCaballos(int idExtra, int idNum) {
		TextView totalView = (TextView)findViewById(idExtra);
		int num = getIntegerValueOfEdit(idNum);
		int total = 0;
		if (num<=3) {
			total = -3;
		} else if (num==5 || num==6) {
			total = 1;
		} else if (num==6 || num==7) {
			total = 2;
		} else if (num>=8) {
			total = num-6;
		}
		totalView.setText(String.valueOf(total));
	}

	private Integer getIntegerValueOfEdit(int id) {
		EditText edit = (EditText)findViewById(id);
		try {
			if (edit.getText().toString()!=null && edit.getText().toString()!="") {
				return Integer.valueOf(edit.getText().toString());
			} else {
				return 0;
			}
		} catch (Exception e) {
			Log.d(TAG, "Error al obtener el valor del campo con id " + id);
			return 0;
		}
	}

	/* ganador */
	private void calcularGanador() {
		if (getTotalJugador1()>getTotalJugador2()) {
			pintarGanadorJugador1();
		} else if (getTotalJugador1()<getTotalJugador2()) {
			pintarGanadorJugador2();
		} else {
			pintarEmpate();
		}
	}

	private int getTotalJugador1() {
		return getIntegerValueOfText(R.id.j1total);
	}

	private int getTotalJugador2() {
		return getIntegerValueOfText(R.id.j2total);
	}

	private Integer getIntegerValueOfText(int id) {
		TextView view = (TextView)findViewById(id);
		try {
			if (view.getText().toString()!=null && view.getText().toString()!="") {
				return Integer.valueOf(view.getText().toString());
			} else {
				return 0;
			}
		} catch (Exception e) {
			Log.d(TAG, "Error al obtener el valor del campo con id " + id);
			return 0;
		}
	}

	private void pintarGanadorJugador1() {
		SharedPreferences settings = getPreferences(0);
		String nombre = settings.getString(ID_NOMBRE_JUGADOR1, getResources().getString(R.string.jugador1));
		pintarGanador(getResources().getString(R.string.gana) + " " + nombre);
	}

	private void pintarGanadorJugador2() {
		SharedPreferences settings = getPreferences(0);
		String nombre = settings.getString(ID_NOMBRE_JUGADOR2, getResources().getString(R.string.jugador2));
		pintarGanador(getResources().getString(R.string.gana) + " " + nombre);
	}

	private void pintarEmpate() {
		pintarGanador(getResources().getText(R.string.empate).toString());
	}

	private void pintarGanador(String texto) {
		TextView ganador = (TextView)findViewById(R.id.resultado);
		ganador.setText(texto);
	}

	/* nombres */
	private void setNombres() {
		SharedPreferences settings = getPreferences(0);
		setNombre(settings, ID_NOMBRE_JUGADOR1, R.string.jugador1, R.id.jugador1);
		setNombre(settings, ID_NOMBRE_JUGADOR2, R.string.jugador2, R.id.jugador2);
	}

	private void setNombre(SharedPreferences settings, String idPreferences, int idDefaultText, int idView) {
		String nombre = settings.getString(idPreferences, getResources().getString(idDefaultText));
        TextView text = (TextView)findViewById(idView);
        text.setText(nombre);
	}

	public void cambiarNombre(final View view) {
		Log.d(TAG, "cambiarNombre");
		Context mContext = getApplicationContext();
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.nombre, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(ContadorActivity.this);
		builder.setView(layout)
			.setTitle(R.string.nombre_titulo)
			.setPositiveButton(R.string.aceptar,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								saveNombre(dialog, view);
								dialog.cancel();
							}
						})
				.setNegativeButton(R.string.cancelar,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		builder.create().show();
	}


	private void saveNombre(DialogInterface dialog, View view) {
		AlertDialog alertDialog = (AlertDialog) dialog;
		EditText edit = (EditText)alertDialog.findViewById(R.id.nombre);
		((TextView)view).setText(edit.getText());

		int idView = view.getId();
		switch (idView) {
		case R.id.jugador1:
			saveSharedNombre(ID_NOMBRE_JUGADOR1, edit.getText());
			break;
		case R.id.jugador2:
			saveSharedNombre(ID_NOMBRE_JUGADOR2, edit.getText());
			break;
		}
	}

	private void saveSharedNombre(String idNombreShared, Editable text) {
		SharedPreferences settings = getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(idNombreShared, text.toString());
		editor.commit();
	}
}