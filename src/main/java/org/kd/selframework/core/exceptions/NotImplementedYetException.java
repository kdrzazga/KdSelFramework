package org.kd.selframework.core.exceptions;

import org.kd.selframework.core.lib.PropertiesReader;

public class NotImplementedYetException extends RuntimeException {

    public NotImplementedYetException(){
        super(new PropertiesReader().readFromConfig("exception.notimplemented").toString());
    }
}
