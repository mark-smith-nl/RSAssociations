package amc.mb.rsassociations.domain;

public abstract class HierarchicalUnit {

	private Long id;

	private String name;

	public HierarchicalUnit(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
}
