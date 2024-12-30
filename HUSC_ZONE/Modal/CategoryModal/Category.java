package CategoryModal;

public class Category {
	private Long CategoryID;
	private String CategoryName;
	private String Image;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Long categoryID, String categoryName, String image) {
		super();
		CategoryID = categoryID;
		CategoryName = categoryName;
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
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
}
