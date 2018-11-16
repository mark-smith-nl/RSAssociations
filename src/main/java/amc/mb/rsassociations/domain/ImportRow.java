package amc.mb.rsassociations.domain;

public abstract class ImportRow {

	protected final Long rowNumber;

	public ImportRow(Long rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Long getRowNumber() {
		return rowNumber;
	}

}
