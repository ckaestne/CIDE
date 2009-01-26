package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName9 extends AnyName {
  public AnyName9(ASTStringNode else_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("else_kw", else_kw)
    }, firstToken, lastToken);
  }
  public AnyName9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName9(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getElse_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("else_kw")).getValue();
  }
}
