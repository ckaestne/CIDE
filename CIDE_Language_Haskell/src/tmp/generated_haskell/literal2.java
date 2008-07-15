package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal2 extends literal {
  public literal2(ASTStringNode float_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("float_kw", float_kw)
    }, firstToken, lastToken);
  }
  public literal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloat_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("float_kw")).getValue();
  }
}
