package model;

public enum DialogState {
    /* Состояния бота */
    WaitMessage,
    WaitURLToAdd,
    WaitURLToDelete;

    public static DialogState map(Integer value) {
        return switch (value) {
            case 0 -> WaitMessage;
            case 1 -> WaitURLToAdd;
            case 2 -> WaitURLToDelete;
            default -> null;
        };
    }
}
