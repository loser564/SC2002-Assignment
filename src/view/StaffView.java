package src.view;

public class StaffView extends MainView {
	public StaffView() {
		super();
	}
	
	@Override
	public void printMenu() {
		System.out.println("1. Create, edit, delete camps");
		System.out.println("2. Toggle camp visibility");
		System.out.println("3. View all camps");
		System.out.println("4. View and reply enquiries");
		System.out.println("5. View and approve suggestions");
		System.out.println("6. Generate attendance or performance report");
		System.out.println("7. Change password");
		System.out.println("8. Logout");
	}
}
