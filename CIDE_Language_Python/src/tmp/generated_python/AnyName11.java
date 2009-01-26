package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName11 extends AnyName {
  public AnyName11(ASTStringNode while_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("while_kw", while_kw)
    }, firstToken, lastToken);
  }
  public AnyName11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName11(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getWhile_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("while_kw")).getValue();
  }
}
