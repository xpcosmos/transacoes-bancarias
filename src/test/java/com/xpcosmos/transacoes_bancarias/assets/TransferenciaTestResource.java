package com.xpcosmos.transacoes_bancarias.assets;

import java.time.LocalDateTime;

import com.xpcosmos.transacoes_bancarias.dto.TransacaoDTO;

public class TransferenciaTestResource extends TestResource<TransacaoDTO> {

	private Long payeeId = Long.valueOf(LocalDateTime.now().hashCode());
	private Long payerId = Long.valueOf(LocalDateTime.now().hashCode());
	private Float value = 0f;

	public TransacaoDTO gerarDTO() {
		return new TransacaoDTO(payeeId, payerId, value);
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public Long getPayerId() {
		return payerId;
	}

	public Float getValue() {
		return value;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public void setPayerId(Long payerId) {
		this.payerId = payerId;
	}

	public void setValue(Float value) {
		this.value = value;
	}

}
