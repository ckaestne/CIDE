package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryOperator4 extends UnaryOperator {
  public UnaryOperator4(ASTStringNode minus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("minus", minus)
    }, firstToken, lastToken);
  }
  public UnaryOperator4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryOperator4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getMinus() {
    return ((PropertyOne<ASTStringNode>)getProperty("minus")).getValue();
  }
}
