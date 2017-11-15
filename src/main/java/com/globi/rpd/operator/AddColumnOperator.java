package com.globi.rpd.operator;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.StandardRpd;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AddColumnOperator implements InputOperator<RpdComponent> {

	
	@Override
	public StandardRpd operate(StandardRpd rpd,List<TableColumnMetadataDTO> dto)   {

		if (rpd == null)
			throw new IllegalStateException("Cannot operate withour a Rpd instance");

		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug(rpd.getName());
		log.debug(dto.get(0).getColName());
		

		return rpd;

	}
	
	
	
	@Override
	public Database operate(Database db,List<TableColumnMetadataDTO> dto)   {

		if (db.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug(db.getName());
		log.debug(dto.get(0).getColName());
		

		return db;

	}
	
	
	
	@Override
	public PhysicalTable operate(PhysicalTable table,List<TableColumnMetadataDTO> dto)   {

		if (table.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		log.debug(table.getName());
		log.debug(dto.get(0).getColName());
		

		return table;

	}


}
