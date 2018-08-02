package address.vo;

public class ListMember {
	private String list_Member_Name;
	private String list_Member_Phone;
	private String list_Member_Address;
	private String list_Member_Birthday;
	private String list_Member_EMail;
	private String list_Member_ImgFile;
	private String member_Id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list_Member_Address == null) ? 0 : list_Member_Address.hashCode());
		result = prime * result + ((list_Member_Birthday == null) ? 0 : list_Member_Birthday.hashCode());
		result = prime * result + ((list_Member_EMail == null) ? 0 : list_Member_EMail.hashCode());
		result = prime * result + ((list_Member_ImgFile == null) ? 0 : list_Member_ImgFile.hashCode());
		result = prime * result + ((list_Member_Name == null) ? 0 : list_Member_Name.hashCode());
		result = prime * result + ((list_Member_Phone == null) ? 0 : list_Member_Phone.hashCode());
		result = prime * result + ((member_Id == null) ? 0 : member_Id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListMember other = (ListMember) obj;
		if (list_Member_Address == null) {
			if (other.list_Member_Address != null)
				return false;
		} else if (!list_Member_Address.equals(other.list_Member_Address))
			return false;
		if (list_Member_Birthday == null) {
			if (other.list_Member_Birthday != null)
				return false;
		} else if (!list_Member_Birthday.equals(other.list_Member_Birthday))
			return false;
		if (list_Member_EMail == null) {
			if (other.list_Member_EMail != null)
				return false;
		} else if (!list_Member_EMail.equals(other.list_Member_EMail))
			return false;
		if (list_Member_ImgFile == null) {
			if (other.list_Member_ImgFile != null)
				return false;
		} else if (!list_Member_ImgFile.equals(other.list_Member_ImgFile))
			return false;
		if (list_Member_Name == null) {
			if (other.list_Member_Name != null)
				return false;
		} else if (!list_Member_Name.equals(other.list_Member_Name))
			return false;
		if (list_Member_Phone == null) {
			if (other.list_Member_Phone != null)
				return false;
		} else if (!list_Member_Phone.equals(other.list_Member_Phone))
			return false;
		if (member_Id == null) {
			if (other.member_Id != null)
				return false;
		} else if (!member_Id.equals(other.member_Id))
			return false;
		return true;
	}
	public String getList_Member_Name() {
		return list_Member_Name;
	}
	public void setList_Member_Name(String list_Member_Name) {
		this.list_Member_Name = list_Member_Name;
	}
	public String getList_Member_Phone() {
		return list_Member_Phone;
	}
	public void setList_Member_Phone(String list_Member_Phone) {
		this.list_Member_Phone = list_Member_Phone;
	}
	public String getList_Member_Address() {
		return list_Member_Address;
	}
	public void setList_Member_Address(String list_Member_Address) {
		this.list_Member_Address = list_Member_Address;
	}
	public String getList_Member_Birthday() {
		return list_Member_Birthday;
	}
	public void setList_Member_Birthday(String list_Member_Birthday) {
		this.list_Member_Birthday = list_Member_Birthday;
	}
	public String getList_Member_EMail() {
		return list_Member_EMail;
	}
	public void setList_Member_EMail(String list_Member_EMail) {
		this.list_Member_EMail = list_Member_EMail;
	}
	public String getList_Member_ImgFile() {
		return list_Member_ImgFile;
	}
	public void setList_Member_ImgFile(String list_Member_ImgFile) {
		this.list_Member_ImgFile = list_Member_ImgFile;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	@Override
	public String toString() {
		return "ListMember [list_Member_Name=" + list_Member_Name + ", list_Member_Phone=" + list_Member_Phone
				+ ", list_Member_Address=" + list_Member_Address + ", list_Member_Birthday=" + list_Member_Birthday
				+ ", list_Member_EMail=" + list_Member_EMail + ", list_Member_ImgFile=" + list_Member_ImgFile
				+ ", member_Id=" + member_Id + "]";
	}
	
	
	
}
