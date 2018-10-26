package amc.mb.rsassociations.domain;

public abstract class ExcelRow {

	protected final Long rowNumber;

	public ExcelRow(Long rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Long getRowNumber() {
		return rowNumber;
	}

}
