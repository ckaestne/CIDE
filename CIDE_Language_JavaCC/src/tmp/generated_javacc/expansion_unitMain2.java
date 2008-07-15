package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unitMain2 extends expansion_unitMain {
  public expansion_unitMain2(regular_expression regular_expression, ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<regular_expression>("regular_expression", regular_expression),
      new PropertyZeroOrOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public expansion_unitMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unitMain2(cloneProperties(),firstToken,lastToken);
  }
  public regular_expression getRegular_expression() {
    return ((PropertyOne<regular_expression>)getProperty("regular_expression")).getValue();
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
