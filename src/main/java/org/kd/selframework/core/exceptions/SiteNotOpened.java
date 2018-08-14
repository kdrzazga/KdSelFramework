package org.kd.selframework.core.exceptions;

import org.kd.selframework.core.lib.PropertiesReader;

public class SiteNotOpened extends RuntimeException {

    public SiteNotOpened(String url, int timeout){
        super(PropertiesReader.readFromConfig("exception.cantload") + url
                + PropertiesReader.readFromConfig("exception.timeout.message") + timeout);
    }
}
