package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName22 extends AnyName {
  public AnyName22(ASTStringNode return_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("return_kw", return_kw)
    }, firstToken, lastToken);
  }
  public AnyName22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName22(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getReturn_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("return_kw")).getValue();
  }
}
