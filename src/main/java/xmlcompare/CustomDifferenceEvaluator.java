package xmlcompare;

import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;
import org.xmlunit.diff.DifferenceEvaluator;

/**
 * Utility class to compare the xml while ignoring the following. 1.Node space
 * is ignored. 2.Attribute value is ignored. 3.Node value is ignored.
 * 
 * @author Subrata Saha (ssaha2)
 *
 */
public class CustomDifferenceEvaluator implements DifferenceEvaluator {
	@Override
	public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {

		switch (comparison.getType()) {
			case CHILD_NODELIST_SEQUENCE:
			case ELEMENT_NUM_ATTRIBUTES:
			case ATTR_VALUE:
			case ATTR_NAME_LOOKUP:
			case TEXT_VALUE:
				outcome = ComparisonResult.EQUAL;
				break;
			default:
				break;
		}
		
		//System.out.println("Comparision type :: "+comparison.getType()+" and Result ::"+outcome);
		return outcome;
	}
	
	
	public ComparisonResult evaluate_ori(Comparison comparison, ComparisonResult outcome) {
		if (outcome == ComparisonResult.EQUAL)
			return outcome;
		
		StringBuilder sb = new StringBuilder();
		sb.append("Comparision type ::").append(comparison.getType());
		
		if(comparison.getType() == ComparisonType.CHILD_NODELIST_SEQUENCE) {
			 System.out.println(sb.toString()+" Result-1 :"+ComparisonResult.EQUAL);
			 return ComparisonResult.EQUAL;
		}

		final Node controlNode = comparison.getControlDetails().getTarget();
		

		if (controlNode != null) {
			
			if (controlNode.getNodeType() == Node.ELEMENT_NODE && controlNode.hasAttributes()) {
				outcome =  ComparisonResult.SIMILAR;
			} else if (controlNode.getNodeType() == Node.ATTRIBUTE_NODE) {
				outcome =  ComparisonResult.SIMILAR;
			} else if (controlNode.getNodeType() == Node.TEXT_NODE) {
				outcome =  ComparisonResult.SIMILAR;
			} 

		}
		System.out.println(sb.toString()+" Result-2 :"+outcome);
		return outcome;
	}
}
