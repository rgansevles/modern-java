package modernjava.domain;

public record Failed(int status, String msg)
    implements Result { }
