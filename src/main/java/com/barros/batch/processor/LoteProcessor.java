package com.barros.batch.processor;

import com.barros.batch.model.Lote;
import com.barros.batch.model.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class LoteProcessor implements ItemProcessor<Lote, Relatorio> {

	private final Logger logger = LoggerFactory.getLogger(LoteProcessor.class);

	@Override
	public Relatorio process(final Lote lote) {
		final Relatorio relatorio = new Relatorio(lote);

		logger.info("Converting (" + lote.getNomeDoArquivo() + ") into (" + relatorio + ")");

		return relatorio;
	}

}
