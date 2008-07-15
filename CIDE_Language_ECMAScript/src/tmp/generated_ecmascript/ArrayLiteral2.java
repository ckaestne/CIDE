package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayLiteral2 extends ArrayLiteral {
  public ArrayLiteral2(ElementList elementList, Elision elision1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ElementList>("elementList", elementList),
      new PropertyOne<Elision>("elision1", elision1)
    }, firstToken, lastToken);
  }
  public ArrayLiteral2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayLiteral2(cloneProperties(),firstToken,lastToken);
  }
  public ElementList getElementList() {
    return ((PropertyOne<ElementList>)getProperty("elementList")).getValue();
  }
  public Elision getElision1() {
    return ((PropertyOne<Elision>)getProperty("elision1")).getValue();
  }
}
