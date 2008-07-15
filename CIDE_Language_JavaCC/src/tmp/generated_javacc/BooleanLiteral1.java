package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BooleanLiteral1 extends BooleanLiteral {
  public BooleanLiteral1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public BooleanLiteral1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BooleanLiteral1(cloneProperties(),firstToken,lastToken);
  }
}
