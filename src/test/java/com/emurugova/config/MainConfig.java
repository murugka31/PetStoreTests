package com.emurugova.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface MainConfig extends Config {

    String hostname();
}
