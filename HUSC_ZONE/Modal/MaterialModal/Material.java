package MaterialModal;

public class Material {
	private Long MaterialID;
	private String MaterialName;
	private String Description;
	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Material(Long materialID, String materialName, String description) {
		super();
		MaterialID = materialID;
		MaterialName = materialName;
		Description = description;
	}
	public Long getMaterialID() {
		return MaterialID;
	}
	public void setMaterialID(Long materialID) {
		MaterialID = materialID;
	}
	public String getMaterialName() {
		return MaterialName;
	}
	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
