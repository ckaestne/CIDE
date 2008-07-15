package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryOperator2 extends UnaryOperator {
  public UnaryOperator2(ASTStringNode star, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("star", star)
    }, firstToken, lastToken);
  }
  public UnaryOperator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryOperator2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getStar() {
    return ((PropertyOne<ASTStringNode>)getProperty("star")).getValue();
  }
}
