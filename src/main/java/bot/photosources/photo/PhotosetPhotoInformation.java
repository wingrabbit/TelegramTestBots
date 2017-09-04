package bot.photosources.photo;

public abstract class PhotosetPhotoInformation extends PhotoInformation {
	
	private String galleryUrl;

	private int photoNumber;		//Position on the page
	
	private String modelName;		//Name of the model
	
	private String modelLink;		//Link to a personal page of the model
	

	public String getGalleryUrl() {
		return galleryUrl;
	}

	public void setGalleryUrl(String galleryUrl) {
		this.galleryUrl = galleryUrl;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelLink() {
		return modelLink;
	}

	public void setModelLink(String modelLink) {
		this.modelLink = modelLink;
	}

	public int getPhotoNumber() {
		return photoNumber;
	}

	public void setPhotoNumber(int photoNumber) {
		this.photoNumber = photoNumber;
	}

}
