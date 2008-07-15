package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal6 extends Literal {
  public Literal6(NullLiteral nullLiteral, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<NullLiteral>("nullLiteral", nullLiteral)
    }, firstToken, lastToken);
  }
  public Literal6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal6(cloneProperties(),firstToken,lastToken);
  }
  public NullLiteral getNullLiteral() {
    return ((PropertyOne<NullLiteral>)getProperty("nullLiteral")).getValue();
  }
}
