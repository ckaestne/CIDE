package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName8 extends AnyName {
  public AnyName8(ASTStringNode if_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("if_kw", if_kw)
    }, firstToken, lastToken);
  }
  public AnyName8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIf_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("if_kw")).getValue();
  }
}
