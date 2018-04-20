package xmlcompare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

public class XMLCompare {
	
	public static void main(String[] args) {
		compareTwoXML();
	}
	
	public static void compareTwoXML(){
	    
		final String src = read("C:\\Users\\ssaha2\\Desktop\\a_src.xml");
		final String dest = read("C:\\Users\\ssaha2\\Desktop\\b_dest.xml");
		
		Diff myDiff = DiffBuilder.compare(src).withTest(dest)
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
				.withDifferenceEvaluator(new CustomDifferenceEvaluator()).ignoreWhitespace().normalizeWhitespace().ignoreComments()
				.checkForSimilar().build(); 
		
		System.out.println("Difference is ::"+myDiff.toString());
	    System.out.println("myDiff.hasDifferences() ::"+myDiff.hasDifferences());
	}
	
	private static String read(String path) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(sb::append);
		} catch (IOException e) {
			// error
		}
		return sb.toString();
	}
}



