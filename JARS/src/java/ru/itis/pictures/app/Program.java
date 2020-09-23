package ru.itis.pictures.app;

import com.beust.jcommander.JCommander;
import ru.itis.pictures.utils.Loader;
import ru.itis.pictures.app.Args;
import java.util.ArrayList;
import java.util.Iterator;

public class Program{
	public static void main(String[] argv){
		Args args = new Args();

		JCommander.newBuilder()
			.addObject(args)
			.build()
			.parse(argv);

		Loader loader = new Loader();

		if (args.mode.equals("onethread")) {

			loader.loadOneThread(args.file, args.folder);

		} else if (args.mode.equals("multithread")) {

			loader.loadMultiThread(args.count, args.file, args.folder);
			
		}
	}
}