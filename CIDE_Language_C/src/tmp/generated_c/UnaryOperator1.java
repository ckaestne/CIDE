package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryOperator1 extends UnaryOperator {
  public UnaryOperator1(ASTStringNode and, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("and", and)
    }, firstToken, lastToken);
  }
  public UnaryOperator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryOperator1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAnd() {
    return ((PropertyOne<ASTStringNode>)getProperty("and")).getValue();
  }
}
