package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class regular_expression2 extends regular_expression {
  public regular_expression2(RegId regId, complex_regular_expression_choices complex_regular_expression_choices, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<RegId>("regId", regId),
      new PropertyOne<complex_regular_expression_choices>("complex_regular_expression_choices", complex_regular_expression_choices)
    }, firstToken, lastToken);
  }
  public regular_expression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new regular_expression2(cloneProperties(),firstToken,lastToken);
  }
  public RegId getRegId() {
    return ((PropertyZeroOrOne<RegId>)getProperty("regId")).getValue();
  }
  public complex_regular_expression_choices getComplex_regular_expression_choices() {
    return ((PropertyOne<complex_regular_expression_choices>)getProperty("complex_regular_expression_choices")).getValue();
  }
}
