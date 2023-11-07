public class User {
	/**
	 * The user's ID
	 */
	protected String _password;
	/**
	 * The user's password
	 */
	protected String _faculty;
	/**
	 * Attributes
	 */
	protected String _userID;

	/**
	 * Constructor
	 */
	public User(String aUserID, String aPassword, String aFaculty) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method to change password
	 */
	public void changePassword(String aNewPassword) {
		throw new UnsupportedOperationException();
	}

	public String getFaculty() {
		return this._faculty;
	}

	public String getUserID() {
		return this._userID;
	}
}