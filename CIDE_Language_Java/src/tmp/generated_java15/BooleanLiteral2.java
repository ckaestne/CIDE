package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BooleanLiteral2 extends BooleanLiteral {
  public BooleanLiteral2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public BooleanLiteral2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BooleanLiteral2(cloneProperties(),firstToken,lastToken);
  }
}
