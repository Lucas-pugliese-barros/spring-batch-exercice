package com.barros.batch.processor;

import com.barros.batch.model.Lote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class LoteItemProcessor implements ItemProcessor<Lote, Lote> {

	private final Logger logger = LoggerFactory.getLogger(LoteItemProcessor.class);

	@Override
	public Lote process(final Lote lote) {
		final Lote loteProcessado = new Lote();

		logger.info("Converting (" + lote + ") into (" + loteProcessado + ")");

		return loteProcessado;
	}

}
