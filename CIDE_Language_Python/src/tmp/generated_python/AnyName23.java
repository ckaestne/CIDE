package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName23 extends AnyName {
  public AnyName23(ASTStringNode yield, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("yield", yield)
    }, firstToken, lastToken);
  }
  public AnyName23(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName23(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getYield() {
    return ((PropertyOne<ASTStringNode>)getProperty("yield")).getValue();
  }
}
