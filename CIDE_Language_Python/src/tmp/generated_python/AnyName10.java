package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyName10 extends AnyName {
  public AnyName10(ASTStringNode elif, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("elif", elif)
    }, firstToken, lastToken);
  }
  public AnyName10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyName10(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getElif() {
    return ((PropertyOne<ASTStringNode>)getProperty("elif")).getValue();
  }
}
