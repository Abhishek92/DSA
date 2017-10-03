
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/*
 * SD2x Homework #10
 * Modify the method below so that it uses defensive programming.
 * Please be sure not to change the method signature!
 */

public class FriendFinder {
	
	protected ClassesDataSource classesDataSource;
	protected StudentsDataSource studentsDataSource;
	
	public FriendFinder(ClassesDataSource cds, StudentsDataSource sds) {
		classesDataSource = cds;
		studentsDataSource = sds;
	}
	
	
	public Set<String> findClassmates(Student theStudent) {

		if(null == theStudent)
			throw new IllegalArgumentException("Student value can not be null");

		String name = theStudent.getName();
		
		// find the classes that this student is taking
		if(null == classesDataSource)
			throw new IllegalStateException();

		if(null == studentsDataSource)
			throw new IllegalStateException();

		if(null == name || name.isEmpty())
			throw new IllegalArgumentException();

		List<String> myClasses = classesDataSource.getClasses(name);


		
		Set<String> classmates = new HashSet<>();
		if(null == myClasses || myClasses.isEmpty())
			return classmates;
		// use the classes to find the names of the students
		for (String myClass : myClasses) {
			// list all the students in the class
			if(null == myClass || myClass.isEmpty())
				continue;

			List<Student> students = studentsDataSource.getStudents(myClass);
			if(null == students || students.isEmpty())
				continue;
			
			for (Student student : students) {
				
				// find the other classes that they're taking
				if(null == student)
					continue;
				else if(null == student.getName() || student.getName().isEmpty())
					continue;
				List<String> theirClasses = classesDataSource.getClasses(student.getName());
				if(null == theirClasses || theirClasses.isEmpty())
					continue;
			
				// see if all of the classes that they're taking are the same as the ones this student is taking
				boolean same = true;
				for (String c : myClasses) {
					if(null == c || c.isEmpty())
						continue;
					if (theirClasses.contains(c) == false) {
						same = false;
						break;
					}
				}
				if (same) {
					if (student.getName().equals(name) == false && classmates.contains(student.getName()) == false) 
						classmates.add(student.getName());
				}
			}

		}
				
		if (classmates.isEmpty()) { 
			return null;
		}
		else return classmates;
	}
	

}
