package componentes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CJTextField extends JTextField {
	
	enum tipo {
		ALFANUMERICO,
		ALF_NUM_MAYUS,
		SOLO_TEXTO,
		SOLO_TEXTO_MAYUS,
		NUMERICO,
		FECHA
	}

	private static final long serialVersionUID = 1L;
	
	public CJTextField (String titulo) {
//        setBorder(new LineBorder(Color.BLACK, 1));
		
		Border BordeConf = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray);
		TitledBorder Borde = BorderFactory.createTitledBorder(BordeConf, titulo);
		Borde.setTitlePosition(TitledBorder.ABOVE_TOP);
		setBorder(Borde);
		
		setBounds(0,0,60,30);
		setColumns(10);
		setBackground(Color.LIGHT_GRAY);
	}

}
