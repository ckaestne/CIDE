package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class complex_regular_expression extends GenASTNode {
  public complex_regular_expression(ArrayList<complex_regular_expression_unit> complex_regular_expression_unit, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<complex_regular_expression_unit>("complex_regular_expression_unit", complex_regular_expression_unit)
    }, firstToken, lastToken);
  }
  public complex_regular_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new complex_regular_expression(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<complex_regular_expression_unit> getComplex_regular_expression_unit() {
    return ((PropertyOneOrMore<complex_regular_expression_unit>)getProperty("complex_regular_expression_unit")).getValue();
  }
}
