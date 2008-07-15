package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class complex_regular_expression_unit4 extends complex_regular_expression_unit {
  public complex_regular_expression_unit4(complex_regular_expression_choices complex_regular_expression_choices, CREUPostfix cREUPostfix, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<complex_regular_expression_choices>("complex_regular_expression_choices", complex_regular_expression_choices),
      new PropertyZeroOrOne<CREUPostfix>("cREUPostfix", cREUPostfix)
    }, firstToken, lastToken);
  }
  public complex_regular_expression_unit4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new complex_regular_expression_unit4(cloneProperties(),firstToken,lastToken);
  }
  public complex_regular_expression_choices getComplex_regular_expression_choices() {
    return ((PropertyOne<complex_regular_expression_choices>)getProperty("complex_regular_expression_choices")).getValue();
  }
  public CREUPostfix getCREUPostfix() {
    return ((PropertyZeroOrOne<CREUPostfix>)getProperty("cREUPostfix")).getValue();
  }
}
