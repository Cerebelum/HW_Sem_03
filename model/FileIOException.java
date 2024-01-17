package HW_Exception.HW_Sem_03.model;

import java.io.IOException;

public class FileIOException extends IOException {
    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
