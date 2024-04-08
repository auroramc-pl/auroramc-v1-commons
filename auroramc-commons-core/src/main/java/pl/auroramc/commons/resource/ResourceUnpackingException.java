package pl.auroramc.commons.resource;

class ResourceUnpackingException extends IllegalStateException {

  ResourceUnpackingException(final String message, final Throwable cause) {
    super(message, cause);
  }

  ResourceUnpackingException(final String message) {
    super(message);
  }
}
