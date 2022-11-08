package goanvi.prog.lab3;

public class WrongDataException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid data";
    }
}
