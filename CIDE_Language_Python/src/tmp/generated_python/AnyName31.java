package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName31 extends AnyName {
  public AnyName31(ASTStringNode as, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("as", as)
    }, firstToken, lastToken);
  }
  public AnyName31(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName31(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAs() {
    return ((PropertyOne<ASTStringNode>)getProperty("as")).getValue();
  }
}
