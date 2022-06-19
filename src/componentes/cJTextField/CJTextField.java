package componentes.cJTextField;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class CJTextField extends JFormattedTextField {

	public static final int ALFANUMERICO = 1;
	public static final int ALF_NUM_MAYUS = 2;
	public static final int SOLO_TEXTO = 3;
	public static final int SOLO_TEXTO_MAYUS = 4;
	public static final int NUMERICO = 5;
	public static final int FECHA = 6;
	public static final int NUMERICO_CEROS = 7;

	private static final long serialVersionUID = 1L;
	private int tipo;
	private String filtro;
	private int longitud;
	private int cantEnteros;
	private int cantDecimales;

	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {

		this.tipo = tipo;
	}
	public AbstractFormatterFactory getFiltro() {
		return getFormatterFactory();
	}
	public void setFiltro(String filtro) {
		try {
			setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(filtro)));
			this.filtro=filtro;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setFiltro(MaskFormatter filtro) {
		setFormatterFactory(new DefaultFormatterFactory(filtro));
		this.filtro=filtro.getMask();
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public int getCantEnteros() {
		return cantEnteros;
	}
	public void setCantEnteros(int cantEnteros) {
		this.cantEnteros = cantEnteros;
	}
	public int getCantDecimales() {
		return cantDecimales;
	}
	public void setCantDecimales(int cantDecimales) {
		this.cantDecimales = cantDecimales;
	}



	public CJTextField (String titulo, int tipo, String filtro) throws Exception {

		Border BordeConf = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray);
		TitledBorder Borde = BorderFactory.createTitledBorder(BordeConf, titulo);
		Borde.setTitlePosition(TitledBorder.ABOVE_TOP);
		setBorder(Borde);

		setLongitud(20);
		setCantEnteros(4);

		setBounds(0,0,60,30);
		setColumns(10);
		setBackground(Color.LIGHT_GRAY);

		if (filtro != null && tipo!=NUMERICO_CEROS)
			setFiltro(filtro);
		else if (filtro != null && tipo==NUMERICO_CEROS)
			throw new Exception("No se puede usar un filtro con el tipo NUMERICO_CEROS.");

		if (filtro == null && (tipo==ALF_NUM_MAYUS || tipo==SOLO_TEXTO || tipo==SOLO_TEXTO_MAYUS || tipo==NUMERICO))
			setDocument(new DocumentCJT(tipo));

		if (tipo==NUMERICO_CEROS) {
			filtro = "#".repeat(cantEnteros);
			
			MaskFormatter mask = new MaskFormatter(filtro);
			mask.setPlaceholderCharacter('0');
			setFiltro(mask);
			
		}


	}

	public CJTextField() throws Exception {
		this(null,ALFANUMERICO,null);
	}

	public CJTextField(int tipo) throws Exception {
		this(null,tipo,null);
	}
	public CJTextField(String titulo, int tipo) throws Exception {
		this(null,tipo,null);
	}

	private void verificaFiltro(String filtro) throws Exception {
		for (int i=0; i<filtro.length(); i++) {
			if (filtro.charAt(i)!='#' && filtro.charAt(i)!='.') {
				throw new Exception("Filtro no valido");
			}
		}
	}

}
