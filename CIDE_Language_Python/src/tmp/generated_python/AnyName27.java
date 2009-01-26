package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName27 extends AnyName {
  public AnyName27(ASTStringNode raise, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("raise", raise)
    }, firstToken, lastToken);
  }
  public AnyName27(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName27(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRaise() {
    return ((PropertyOne<ASTStringNode>)getProperty("raise")).getValue();
  }
}
