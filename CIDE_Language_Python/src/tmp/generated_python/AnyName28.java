package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName28 extends AnyName {
  public AnyName28(ASTStringNode global, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("global", global)
    }, firstToken, lastToken);
  }
  public AnyName28(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName28(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getGlobal() {
    return ((PropertyOne<ASTStringNode>)getProperty("global")).getValue();
  }
}
