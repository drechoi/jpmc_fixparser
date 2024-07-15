package com.example.andre.fixparser.api.utils;

/**
 * For demo reason, some values are hard coded here
 * normally some of them are configurable
 * */
public class Constants {

    public static final byte WILDCARD_BYTE = '*';
    public static final byte DEFAULT_SEPARATOR = '|';

    /**
     * max length allowed for a fix msg
     */
    public static final short MAX_FIX_MSG_LENGTH = 1000;

    /**
     * max length allowed for a fix field
     * */
    public static final int MAX_FIELD_SIZE=255;

    /**
     * max length allowed for a fix field
     * */
    public static final int OBJECT_POOL_SIZE=100000;

    /**
     * OBJECT_POOL_MODE:
     *
     * for object pool out of object handling
     *
     * 0 - overwrite old object
     * 1 - throw exception
     *
     */
    public static final int OBJECT_POOL_MODE = 0;

    public static final boolean DEBUG = false;
}
