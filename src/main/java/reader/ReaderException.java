package reader;

public class ReaderException extends RuntimeException {
  ReaderException(String message) {
    super(message);
  }

  /**
   * Detailed message is not automatically incorporated into the RuntimeExceptions output. You
   * need to use getMessage of RuntimeExeption to get that detail.
   *
   * @param message
   * @param e
   */
  ReaderException(String message, Exception e) {
    super(message, e);
  }
}
