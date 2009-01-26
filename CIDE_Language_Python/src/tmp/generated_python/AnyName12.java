package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName12 extends AnyName {
  public AnyName12(ASTStringNode for_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("for_kw", for_kw)
    }, firstToken, lastToken);
  }
  public AnyName12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName12(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFor_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("for_kw")).getValue();
  }
}
