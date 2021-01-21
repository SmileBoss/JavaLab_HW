package ru.itis;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = {"--port", "--p"})
    public int port;

    @Parameter(names = {"--host", "--h"})
    public String host;
}
