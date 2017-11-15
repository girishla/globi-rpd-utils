
package com.globi.rpd.operator;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalForeignKey;
import com.globi.rpd.component.PhysicalKey;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.component.StandardRpd;
import com.globi.rpd.traverser.InputTraverser;
import com.globi.rpd.traverser.TraversingOperatorProgressMonitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreadthFirstTraversingInputOperator implements InputOperator<RpdComponent> {

	private boolean traverseFirst = false;
	private InputOperator<? extends RpdComponent> operator;
	private InputTraverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;
	private List<TableColumnMetadataDTO> dto;

	public BreadthFirstTraversingInputOperator(InputTraverser aTraverser, InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> aDto) {
		traverser = aTraverser;
		operator = anOperator;
		dto=aDto;
	}

	@Override
	public StandardRpd operate(StandardRpd rpd,List<TableColumnMetadataDTO> dto) {

		StandardRpd returnVal;
		returnVal = (StandardRpd) rpd.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), rpd);
		}
		traverser.traverse((StandardRpd) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), rpd);
		}
		return returnVal;
	}
	
	
	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog,List<TableColumnMetadataDTO> dto) {
		PresentationCatalog returnVal;
		returnVal = (PresentationCatalog) presCatalog.applyWithInput(operator,dto);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presCatalog);
		}
		traverser.traverse((PresentationCatalog) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presCatalog);
		}
		return returnVal;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable,List<TableColumnMetadataDTO> dto) {

		PresentationTable returnVal;
		returnVal = (PresentationTable) presTable.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presTable);
		}
		traverser.traverse((PresentationTable) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presTable);
		}
		return returnVal;
	}

	@Override
	public PresentationColumn operate(PresentationColumn presColumn,List<TableColumnMetadataDTO> dto) {

		PresentationColumn returnVal;
		returnVal = presColumn.applyWithInput(operator,dto);
		if (progressMonitor != null) {
			// progressMonitor.operated(operator.getClass().getName(),
			// presColumn);
		}
		traverser.traverse((PresentationColumn) returnVal, this,dto);
		if (progressMonitor != null) {
			// progressMonitor.traversed(traverser.getClass().getName(),
			// presColumn);
		}
		return returnVal;
	}

	@Override
	public BusinessModel operate(BusinessModel model,List<TableColumnMetadataDTO> dto) {

		BusinessModel returnVal;
		returnVal = (BusinessModel) model.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), model);
		}
		traverser.traverse((BusinessModel) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), model);
		}
		return returnVal;
	}

	@Override
	public LogicalTable operate(LogicalTable table,List<TableColumnMetadataDTO> dto) {

		LogicalTable returnVal;
		returnVal = (LogicalTable) table.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), table);
		}
		traverser.traverse((LogicalTable) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), table);
		}
		return returnVal;
	}

	@Override
	public PresentationHierarchy operate(PresentationHierarchy presHierarchy,List<TableColumnMetadataDTO> dto) {

		PresentationHierarchy returnVal;
		returnVal = (PresentationHierarchy) presHierarchy.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presHierarchy);
		}
		traverser.traverse((PresentationHierarchy) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presHierarchy);
		}
		return returnVal;
	}
	
	
	@Override
	public Database operate(Database db,List<TableColumnMetadataDTO> dto) {

		Database returnVal;
		returnVal = (Database) db.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), db);
		}
		traverser.traverse((Database) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), db);
		}
		return returnVal;
	}
	
	
	@Override
	public ConnectionPool operate(ConnectionPool cp,List<TableColumnMetadataDTO> dto) {

		ConnectionPool returnVal;
		returnVal = (ConnectionPool) cp.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), cp);
		}
		traverser.traverse((ConnectionPool) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), cp);
		}
		return returnVal;
	}
	
	@Override
	public Schema operate(Schema schema,List<TableColumnMetadataDTO> dto) {

		Schema returnVal;
		returnVal = (Schema) schema.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), schema);
		}
		traverser.traverse((Schema) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), schema);
		}
		return returnVal;
	}

	@Override
	public PhysicalTable operate(PhysicalTable table,List<TableColumnMetadataDTO> dto) {

		PhysicalTable returnVal;
		returnVal = (PhysicalTable) table.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), table);
		}
		traverser.traverse((PhysicalTable) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), table);
		}
		return returnVal;
	}

	@Override
	public PhysicalColumn operate(PhysicalColumn col,List<TableColumnMetadataDTO> dto) {

		PhysicalColumn returnVal;
		returnVal = (PhysicalColumn) col.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), col);
		}
		traverser.traverse((PhysicalColumn) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), col);
		}
		return returnVal;
	}

	@Override
	public PhysicalKey operate(PhysicalKey key,List<TableColumnMetadataDTO> dto) {

		PhysicalKey returnVal;
		returnVal = (PhysicalKey) key.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), key);
		}
		traverser.traverse((PhysicalKey) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), key);
		}
		return returnVal;
	}


	@Override
	public PhysicalForeignKey operate(PhysicalForeignKey key,List<TableColumnMetadataDTO> dto) {

		PhysicalForeignKey returnVal;
		returnVal = (PhysicalForeignKey) key.applyWithInput(operator,dto);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), key);
		}
		traverser.traverse((PhysicalForeignKey) returnVal, this,dto);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), key);
		}
		return returnVal;
	}

	
	
	
}
