package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName20 extends AnyName {
  public AnyName20(ASTStringNode break_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("break_kw", break_kw)
    }, firstToken, lastToken);
  }
  public AnyName20(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName20(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getBreak_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("break_kw")).getValue();
  }
}
