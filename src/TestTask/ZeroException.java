package TestTask;

import java.io.IOException;

class ZeroException extends IOException {
    public ZeroException (String description){
        super((description));
    }
}
