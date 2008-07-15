package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryOperator5 extends UnaryOperator {
  public UnaryOperator5(ASTStringNode tilde, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("tilde", tilde)
    }, firstToken, lastToken);
  }
  public UnaryOperator5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryOperator5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTilde() {
    return ((PropertyOne<ASTStringNode>)getProperty("tilde")).getValue();
  }
}
