package spoondemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.model.JavaType;

public class QueryingStatements {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		String fileFullPath = "C:\\Arpit Bhatnagar\\POC_Workspace\\demo\\src\\main\\java\\Qdox\\CodeForJDBC.java";
		JavaProjectBuilder builder = new JavaProjectBuilder();
		JavaSource src = builder.addSource(new FileReader(fileFullPath));
		JavaPackage pkg = src.getPackage();
		JavaClass class1 = src.getClasses().get(0); // getting class name
		Class<? extends JavaSource> class2 = src.getClass();
//		System.out.println("Class Name is: " + class1.getName()); // printing class name
		JavaClass cls = builder.getClassByName(class1.toString());
//		System.out.println("Fully Qualified Class Name: " + cls);
		Collection<JavaSource> srcs = builder.getSources();
		for (JavaSource src1 : srcs) {
			List<String> imports = src1.getImports();

			for (String imp : imports) // printing imports used
			{
				if (imp.contains("javax") || imp.contains("persistence")) {
					System.out.println("SUCCESS - JPA Application Found");
					System.out.println("*******************************");
				} else if (imp.contains("sql")) {
					System.out.println("SUCCESS - JDBC Application Found");
					System.out.println("*******************************");
				} else {
					System.out.println("FAILURE - File found with no relevant imports");
					System.out.println("*******************************");
				}
			}

//			System.out.println("Import statements are :");
//			for (String imp : imports) // printing imports used
//			{
//				System.out.println("\t\t" + imp);
//
//			}
		}
		File file = new File(fileFullPath);

		try {
			Scanner scanner = new Scanner(file);
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNum++;
				if (line.contains("executeQuery")) {
					Path path = Paths.get(fileFullPath);

					Path fileName = path.getFileName();

					System.out.println("SUCCESS - CRUD operation found in file = " + fileName.toString()
							+ " and in line number = " + lineNum);
					System.out.println("*******************************");
					Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(line);
					while (m.find()) {
						System.out.println("SUCCESS - Parameter passed in executeQuery is = " + m.group(1));
						System.out.println("*******************************");
						if (m.group(1).toLowerCase().contains("select")) {
							System.out.println("Direct query found");
						} else {
							System.out.println("INFO - Direct query not passed = Please write logic for call stack.");

						}
					}
					System.out.println("*******************************");

				}
			}
			try {
				File file1 = new File(fileFullPath);
				Scanner scanner1 = new Scanner(file1);
				while (scanner1.hasNextLine()) {
					String line1 = scanner1.nextLine();
					if (line1.toLowerCase().contains("query")) {
						Pattern p = Pattern.compile("\"([^\"]*)\"");
						Matcher m = p.matcher(line1);
						while (m.find()) {
						  System.out.println("SUCCESS - Final query found = " + m.group(1));
						  System.out.println("*******************************");
						}
						break;
					}
				}
			} catch (Exception e) {

			}
		} catch (FileNotFoundException e) {
		}

	}

}