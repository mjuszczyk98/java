package lab3;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static final String PATH = "/Users/michal/Desktop/Main/JAVA/Lab1/bin";
	
	public static final int THREADS = 5;

	public static void main(String[] args) {		
		for(int i = 0; i < Main.THREADS; i++)
			new Thread(new ThreadLogic()).start();
		
		Thread t = new Thread(()-> {
			while(ThreadLogic.running()) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {System.out.println("??");}
				synchronized(System.out) {
					System.out.println(ThreadLogic.tmpRatio() + "\t" + ThreadLogic.ratio());
				}
				ThreadLogic.cleanTmpRatio();
			}
		});
		t.setPriority(6);
		t.start();
		
		new Thread(()-> {
			while(ThreadLogic.running()) {
				try {
					System.in.read();
					synchronized(Main.methods) {
						Main.getSortingFunctions(Main.PATH);
					}
				} catch (Exception e) {System.out.println("??");}
			}
		});
		
		synchronized(Main.methods) {
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}
			Main.getSortingFunctions(Main.PATH);
		}
	}
	
	
	
	public static List<Method> methods = new ArrayList<Method>();
	public static List<Class<?>> classes = new ArrayList<Class<?>>();

	public static void getSortingFunctions(String path) {
		try {
			Main.methods.clear();
			Main.classes.clear();
			
			File f = new File(path);
			URL url = f.toURI().toURL();
			URL[] urlArray = {url};
			URLClassLoader ucl = new URLClassLoader(urlArray);
			
			Stream<Path> paths = Files.walk(Paths.get(f.toURI()));
			List<String> list = paths.map(p -> p.toString()).collect(Collectors.toList());
			list = list
					.stream()
					.map(x -> x.replace(path + "/", ""))
					.filter(x -> x.endsWith(".class"))
					.map(x -> x.replace(".class", ""))
					.map(x -> x.replace("/", "."))
					.collect(Collectors.toList());
			
			for (String p : list) {
				Class<?> c = ucl.loadClass(p);
				if(c.getName().contains("Abstract"))
					continue;
				Method[] methArray = c.getDeclaredMethods();
				Method method = null;
				for(Method m : methArray) {
					if(m.getName() == "solve2")
						method = m;
					if(m.getName() == "solve" && method == null)
						method = m;
				}
				if(method != null) {
					methods.add(method);
					classes.add(c);
				}
			}
			
			ucl.close();
			paths.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
