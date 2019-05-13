package presenter;

public interface LobbyPresenter {
    void writeMsg(String msg);
    void startGame();
    void show();
    void ready(boolean isRdy);
}
