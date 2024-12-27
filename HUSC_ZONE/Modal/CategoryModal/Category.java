package CategoryModal;

public class Category {
	private Long CategoryID;
	private String CategoryName;
	private String Description;
	private String Image;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Long categoryID, String categoryName, String description, String image) {
		super();
		CategoryID = categoryID;
		CategoryName = categoryName;
		Description = description;
		Image = image;
	}
	public Long getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(Long categoryID) {
		CategoryID = categoryID;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
}
