package fr.dd06.apis.minewebauth.auth.exception;

public class DataEmptyException extends Exception {
   public DataEmptyException() {
   }

   public DataEmptyException(String message) {
      super(message);
   }

   public DataEmptyException(String message, Throwable cause) {
      super(message, cause);
   }

   public DataEmptyException(Throwable cause) {
      super(cause);
   }
}
