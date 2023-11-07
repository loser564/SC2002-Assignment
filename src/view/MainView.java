package src.view;

public abstract class MainView {
    protected abstract void viewApp();
    public abstract void printMenu();

    public MainView(){
        this.viewApp();
    }
}
