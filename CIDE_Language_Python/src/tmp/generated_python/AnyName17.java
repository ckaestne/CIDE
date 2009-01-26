package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName17 extends AnyName {
  public AnyName17(ASTStringNode finally_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("finally_kw", finally_kw)
    }, firstToken, lastToken);
  }
  public AnyName17(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName17(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFinally_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("finally_kw")).getValue();
  }
}
