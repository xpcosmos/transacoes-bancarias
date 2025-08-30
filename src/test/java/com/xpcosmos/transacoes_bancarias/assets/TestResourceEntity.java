package com.xpcosmos.transacoes_bancarias.assets;

public abstract class TestResourceEntity<T, K> extends TestResource<T> {

	public abstract K gerarEntity();
}
