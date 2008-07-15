package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayLiteral1 extends ArrayLiteral {
  public ArrayLiteral1(Elision elision, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Elision>("elision", elision)
    }, firstToken, lastToken);
  }
  public ArrayLiteral1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayLiteral1(cloneProperties(),firstToken,lastToken);
  }
  public Elision getElision() {
    return ((PropertyZeroOrOne<Elision>)getProperty("elision")).getValue();
  }
}
