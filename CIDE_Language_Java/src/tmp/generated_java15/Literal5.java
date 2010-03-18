package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal5 extends Literal {
  public Literal5(BooleanLiteral booleanLiteral, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BooleanLiteral>("booleanLiteral", booleanLiteral)
    }, firstToken, lastToken);
  }
  public Literal5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal5(cloneProperties(),firstToken,lastToken);
  }
  public BooleanLiteral getBooleanLiteral() {
    return ((PropertyOne<BooleanLiteral>)getProperty("booleanLiteral")).getValue();
  }
}
