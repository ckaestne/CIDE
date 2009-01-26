package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName5 extends AnyName {
  public AnyName5(ASTStringNode is, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("is", is)
    }, firstToken, lastToken);
  }
  public AnyName5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIs() {
    return ((PropertyOne<ASTStringNode>)getProperty("is")).getValue();
  }
}
