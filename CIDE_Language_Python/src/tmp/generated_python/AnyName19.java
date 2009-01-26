package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName19 extends AnyName {
  public AnyName19(ASTStringNode pass, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pass", pass)
    }, firstToken, lastToken);
  }
  public AnyName19(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName19(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPass() {
    return ((PropertyOne<ASTStringNode>)getProperty("pass")).getValue();
  }
}
