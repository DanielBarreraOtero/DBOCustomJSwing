package componentes.cJTextField;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DocumentCJT extends PlainDocument {

	private static final long serialVersionUID = 1L;
	public static final int ALFANUMERICO = 1;//done
	public static final int ALF_NUM_MAYUS = 2;//done
	public static final int SOLO_TEXTO = 3;//done
	public static final int SOLO_TEXTO_MAYUS = 4;//done
	public static final int NUMERICO = 5;
	public static final int FECHA = 6;
	public static final int NUMERICO_CEROS = 7;

	protected int tipo = 0;
	protected boolean aceptaNegativo = false;

	public DocumentCJT() {
		this(ALFANUMERICO);
	}
	public DocumentCJT(int tipo) {
		this.tipo = tipo;
	}

	public void setNegativeAccepted(boolean aceptaNegativo) {
		if (tipo==NUMERICO || tipo==NUMERICO_CEROS){
			this.aceptaNegativo = aceptaNegativo;
		}
	}

	public void insertString (int offset, String  str, AttributeSet attr) throws BadLocationException {
		if (str == null) return;

		if (tipo==ALF_NUM_MAYUS || tipo==SOLO_TEXTO || tipo==SOLO_TEXTO_MAYUS) {
			
			if ((tipo==SOLO_TEXTO ||  tipo==SOLO_TEXTO_MAYUS) && tieneDigits(str))
				return;
			else if (tipo==ALF_NUM_MAYUS || tipo==SOLO_TEXTO_MAYUS)
				str = str.toUpperCase();
		}
		
		if (tipo==NUMERICO || tipo==NUMERICO_CEROS) {
			if (!tieneDigits(str) || (aceptaNegativo && str.charAt(0)!='-')) {
				return;
			}
		}

		super.insertString(offset, str, attr);
	}
	
	private boolean tieneDigits(String str) {
		boolean tieneDigits = false;
		
		for(int i = 0; i<str.length(); i++) {
			if(Character.isDigit(str.charAt(i)))
				tieneDigits = true;
		}
		
		return tieneDigits;
	}
}
