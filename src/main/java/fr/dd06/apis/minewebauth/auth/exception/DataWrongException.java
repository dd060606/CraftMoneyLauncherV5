package fr.dd06.apis.minewebauth.auth.exception;

public class DataWrongException extends Exception {
   public DataWrongException() {
   }

   public DataWrongException(String message) {
      super(message);
   }

   public DataWrongException(String message, Throwable cause) {
      super(message, cause);
   }

   public DataWrongException(Throwable cause) {
      super(cause);
   }
}
