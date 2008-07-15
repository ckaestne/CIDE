package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryOperator6 extends UnaryOperator {
  public UnaryOperator6(ASTStringNode not, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("not", not)
    }, firstToken, lastToken);
  }
  public UnaryOperator6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryOperator6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNot() {
    return ((PropertyOne<ASTStringNode>)getProperty("not")).getValue();
  }
}
