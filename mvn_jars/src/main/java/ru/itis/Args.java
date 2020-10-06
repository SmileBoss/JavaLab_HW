package ru.itis;

import com.beust.jcommander.*;
// import com.beust.jcommander.converters.IParameterSplitter;
import java.util.*;

@Parameters(separators = "=")
public class Args {



    @Parameter(names = {"--mode"})
    public String mode;

    @Parameter(names = {"--count"})
    public Integer count;

    @Parameter(
            names = {"--files"}
            // splitter = SemicolonSplitter.class
    )
    public List<String> file = new ArrayList<>();

    @Parameter(names = {"--folder"})
    public String folder;

}

// class SemicolonSplitter implements IParameterSplitter {

//     @Override
//     public List<String> split(String value) {
//         return Arrays.asList(value.split(";"));
//     }
// }

