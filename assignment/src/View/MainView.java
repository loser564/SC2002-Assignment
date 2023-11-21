package View;

public abstract class MainView {
    protected abstract void viewApp();
    public abstract void printMenu();

    public MainView(String userID, String password){
        this.viewApp();
    }
}
