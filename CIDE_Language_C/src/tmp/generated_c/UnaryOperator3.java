package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryOperator3 extends UnaryOperator {
  public UnaryOperator3(ASTStringNode plus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("plus", plus)
    }, firstToken, lastToken);
  }
  public UnaryOperator3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryOperator3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPlus() {
    return ((PropertyOne<ASTStringNode>)getProperty("plus")).getValue();
  }
}
