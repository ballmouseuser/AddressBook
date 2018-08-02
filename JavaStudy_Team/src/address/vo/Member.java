package address.vo;

public class Member {
	private String member_id;
	private String member_name;
	private String member_password;
	private String member_address;
	private String member_registnumber;
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String txtMemberpassword) {
		this.member_password = txtMemberpassword;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_registnumber() {
		return member_registnumber;
	}
	public void setMember_registnumber(String member_registnumber) {
		this.member_registnumber = member_registnumber;
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_name=" + member_name + ", member_password="
				+ member_password + ", member_address=" + member_address + ", member_registnumber="
				+ member_registnumber + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_address == null) ? 0 : member_address.hashCode());
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
		result = prime * result + ((member_name == null) ? 0 : member_name.hashCode());
		result = prime * result + ((member_password == null) ? 0 : member_password.hashCode());
		result = prime * result + ((member_registnumber == null) ? 0 : member_registnumber.hashCode());
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
		Member other = (Member) obj;
		if (member_address == null) {
			if (other.member_address != null)
				return false;
		} else if (!member_address.equals(other.member_address))
			return false;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		if (member_name == null) {
			if (other.member_name != null)
				return false;
		} else if (!member_name.equals(other.member_name))
			return false;
		if (member_password == null) {
			if (other.member_password != null)
				return false;
		} else if (!member_password.equals(other.member_password))
			return false;
		if (member_registnumber == null) {
			if (other.member_registnumber != null)
				return false;
		} else if (!member_registnumber.equals(other.member_registnumber))
			return false;
		return true;
	}
	
	
}
