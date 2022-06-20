package componentes.cJTextField;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;

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
	public String getFiltro() {
		return filtro;
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

		if (filtro != null && tipo!=NUMERICO_CEROS) //No puede ser numerico ceros y tener un filtro, ya que numerico ceros  
			setFiltro(filtro);							//ya usa un filtro y esto podriacrear conflictos
		else if (filtro != null && tipo==NUMERICO_CEROS)
			throw new Exception("No se puede usar un filtro con el tipo NUMERICO_CEROS.");

		if (filtro == null && (tipo==ALF_NUM_MAYUS || tipo==SOLO_TEXTO || tipo==SOLO_TEXTO_MAYUS || tipo==NUMERICO))
			setDocument(new DocumentCJT(tipo));	//si usa cualquiera de estos tipos el documento se encarga de manejarlos
		else if (tipo==NUMERICO_CEROS) { //sino, se crea un filtro para controlarlos
			filtro = "#".repeat(cantEnteros);
			
			MaskFormatter mask = new MaskFormatter(filtro);
			mask.setPlaceholderCharacter('0');
			setFiltro(mask);
			
		}
		else if (tipo==FECHA) {
			filtro = "##/##/####";

			MaskFormatter mask = new MaskFormatter(filtro);
			mask.setPlaceholderCharacter('0');
			setFiltro(mask);
			
			addFocusListener(new FocusAdapter() { // añadimos un evento para validar la fecha cuando se pierda el foco
				@Override
				public void focusLost(FocusEvent e) {
					int dia, mes, año;
					
					dia = Integer.parseInt(getText().substring(0, 2));
					mes = Integer.parseInt(getText().substring(3, 5));
					año = Integer.parseInt(getText().substring(6, 10));
					
					try {
						LocalDate fecha = LocalDate.of(año, mes, dia);
						setForeground(Color.green); // si la fecha esta bien, se pone el texto en verde
					} catch (Exception e2) {
						setForeground(Color.red);	// si la fecha esta mal, se pone el texto en rojo
					}
				}
			});
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
