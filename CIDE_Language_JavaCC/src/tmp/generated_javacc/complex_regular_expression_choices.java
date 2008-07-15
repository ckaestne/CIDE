package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class complex_regular_expression_choices extends GenASTNode {
  public complex_regular_expression_choices(ArrayList<complex_regular_expression> complex_regular_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<complex_regular_expression>("complex_regular_expression", complex_regular_expression)
    }, firstToken, lastToken);
  }
  public complex_regular_expression_choices(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new complex_regular_expression_choices(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<complex_regular_expression> getComplex_regular_expression() {
    return ((PropertyList<complex_regular_expression>)getProperty("complex_regular_expression")).getValue();
  }
}
