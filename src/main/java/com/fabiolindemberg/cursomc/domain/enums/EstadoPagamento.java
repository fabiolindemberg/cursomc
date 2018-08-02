package com.fabiolindemberg.cursomc.domain.enums;

public enum EstadoPagamento {
	PEDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancalado");
	
	private Integer cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for(EstadoPagamento estadoPagamento: EstadoPagamento.values()) {
			if(estadoPagamento.cod.equals(cod)) {
				return estadoPagamento;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + cod );
	}
	
}
