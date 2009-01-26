package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName6 extends AnyName {
  public AnyName6(ASTStringNode in, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("in", in)
    }, firstToken, lastToken);
  }
  public AnyName6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIn() {
    return ((PropertyOne<ASTStringNode>)getProperty("in")).getValue();
  }
}
