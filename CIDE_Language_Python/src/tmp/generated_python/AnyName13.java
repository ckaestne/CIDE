package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName13 extends AnyName {
  public AnyName13(ASTStringNode try_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("try_kw", try_kw)
    }, firstToken, lastToken);
  }
  public AnyName13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName13(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTry_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("try_kw")).getValue();
  }
}
