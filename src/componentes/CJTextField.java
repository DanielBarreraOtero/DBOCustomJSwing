package componentes;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private int tipo;
	private String filtro;
	
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
			verificaFiltro(filtro);
			this.filtro = filtro;
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public CJTextField (String titulo, int tipo) {
		
		Border BordeConf = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.black,Color.gray);
		TitledBorder Borde = BorderFactory.createTitledBorder(BordeConf, titulo);
		Borde.setTitlePosition(TitledBorder.ABOVE_TOP);
		setBorder(Borde);
		
		setBounds(0,0,60,30);
		setColumns(10);
		setBackground(Color.LIGHT_GRAY);
		
		switch (tipo) {
			case 2: {
				addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						//Cambia el caracter recibido a mayusculas.
						e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
					}
				});
				break;
			}
			case 3: {
				addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						if (Character.isDigit(e.getKeyChar()))
							e.consume();
					}
				});
				break;
			}
			case 4: {
				addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						if (Character.isDigit(e.getKeyChar()))
							e.consume();
						else
							e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
					}
				});
				break;
			}
			case 5: {
				addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e){
						if (filtro != null) {
							//TODO hacer modo numerico con filtro
						} else {
							//Comprueba si el input es un numero o un punto, si no, lo borra
							if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!='.') 
								e.consume(); 
							//Despues comprueba si es un punto y si ya existe uno en la cadena, si es asi, lo borra
							if (e.getKeyChar()=='.' && getText().contains("."))
								e.consume(); 
						}
					}
				});
				break;
			}
		}
	}
	
	private void verificaFiltro(String filtro) throws Exception {
		for (int i=0; i<filtro.length(); i++) {
			if (filtro.charAt(i)!='0' && filtro.charAt(i)!='.') {
				throw new Exception("Filtro no valido");
			}
		}
	}

}
