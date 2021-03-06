package state;

public class MaquinaDoPokemon {

	private static int SEM_BRINQUEDOS = 0;
	private static int SEM_MOEDA = 1;
	private static int COM_MOEDA = 2;
	private static int VENDIDO = 3;
	
	private int state = SEM_BRINQUEDOS;
	private int totalDeBrinquedos = 0;
	
	public MaquinaDoPokemon() {
		
	}

	public MaquinaDoPokemon(int qtd) {
		colocarBrinquedos(qtd);
	}

	public String colocarMoeda(){
		if(state == COM_MOEDA){
			return "Cabe apenas uma moeda, jah tem uma.";
		}else if(state == SEM_BRINQUEDOS){
			return "Nao dah para colocar a moeda. Maquina vazia";
		}else if(state == VENDIDO){
			return "Um momento, peque o brinquedo primeiro.";
		}else if(state == SEM_MOEDA){
			state = COM_MOEDA;
			return "Ok, moeda recebida.";
		}else{
			throw new RuntimeException("Estado desconhecido " + state);
		}
	}
	
	public String devolverMoeda(){
		if(state == COM_MOEDA){
			state = SEM_MOEDA;
			return "Moeda devolvida.";
		}else if(state == SEM_BRINQUEDOS){
			return "Vc nao colocou uma moeda.";
		}else if(state == VENDIDO){
			return "Desculpe, mas vc jah virou a alavanca...";
		}else if(state == SEM_MOEDA){
			return "Vc nao colocou uma moeda.";
		}else{
			throw new RuntimeException("Estado desconhecido " + state);
		}
	}

	public String acionarAlavanca(){
		if(state == COM_MOEDA){
			state = VENDIDO;
			return "Vc vira a alavanca e " + entregarBrinquedo();
		}else if(state == SEM_BRINQUEDOS){
			return "Vc girou, mas os brinquedos acabaram.";
		}else if(state == VENDIDO){
			return "Vc nao vai ganhar outro brinquedo girando 2x.";
		}else if(state == SEM_MOEDA){
			return "Vc girou, mas sem moeda.";
		}else{
			throw new RuntimeException("Estado desconhecido " + state);
		}
	}
	
	public String entregarBrinquedo(){
		String mensagem = "";
		if(state == VENDIDO){
			mensagem += "um brinquedo aparece!";
			totalDeBrinquedos --;
			if(totalDeBrinquedos == 0){
				state = SEM_BRINQUEDOS;
				mensagem += "Os brinquedos acabaram";
			}else{
				state = SEM_MOEDA;
			}
		}else if(state == SEM_MOEDA){
			mensagem += "Coloque a moeda primeiro.";
		}else if(state == SEM_BRINQUEDOS){
			mensagem += "Maquina vazia";
		}else if(state == COM_MOEDA){
			mensagem += "Gire a alavanca, ou pegue a moeda de volta.";
		}
		return mensagem;
	}
	
	public String colocarBrinquedos(int qtd){
		assert qtd > 0;
		state = SEM_MOEDA;
		totalDeBrinquedos += qtd;
		return "Maquina com " + qtd + " brinquedo(s).";
	}
	
}
