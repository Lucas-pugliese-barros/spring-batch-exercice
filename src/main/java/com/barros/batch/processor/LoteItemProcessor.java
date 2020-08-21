package com.barros.batch.processor;

import com.barros.batch.model.Lote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class LoteItemProcessor implements ItemProcessor<Lote, Lote> {

	private static final Logger log = LoggerFactory.getLogger(LoteItemProcessor.class);

	@Override
	public Lote process(final Lote lote) throws Exception {
		final Lote loteProcessado = new Lote();

		log.info("Converting (" + lote + ") into (" + loteProcessado + ")");

		return loteProcessado;
	}

}
