package TestTask;

import java.io.IOException;

public class DifferentDataInException extends IOException {

    public DifferentDataInException(String description){
        super((description));
    }
}