package spoondemo;


import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtField;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonStarter {
	
	
	public static void main(String[] args) {

		String empID ="SELECT * FROM EMPOYEE WHERE EMPLOYEE ID = '1002'";		 
	  
Launcher launcher = new Launcher();
		// launcher.addInputResource("C:\\SPOON_Workspace\\spoon\\src\\main\\java\\jdbc\\StaticCodeForJDBC.java");
		launcher.addInputResource("C:\\Users\\Admin\\Desktop\\spoon\\src\\main\\java\\spoondemo\\DynamicCodeForJDBC.java");
		launcher.buildModel();
		CtModel model = launcher.getModel();
		 
		//global variable
		
		/*for (CtField<?> variable : model.getElements(new TypeFilter<>(CtField.class))) {
        System.out.print(variable.getPath()+"\t\t");
		System.out.print("\n"+variable.getAssignment());
			}*/
		//local variable
		for (CtLocalVariable<?> variable : model.getElements(new TypeFilter<>(CtLocalVariable.class))) {
			System.out.print(variable.getPath()+"\t\t");
			System.out.print("\n"+variable.getAssignment());
				} 
 
}
} 