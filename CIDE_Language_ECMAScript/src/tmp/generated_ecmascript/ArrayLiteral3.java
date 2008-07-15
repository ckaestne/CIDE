package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArrayLiteral3 extends ArrayLiteral {
  public ArrayLiteral3(ElementList elementList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ElementList>("elementList1", elementList1)
    }, firstToken, lastToken);
  }
  public ArrayLiteral3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArrayLiteral3(cloneProperties(),firstToken,lastToken);
  }
  public ElementList getElementList1() {
    return ((PropertyZeroOrOne<ElementList>)getProperty("elementList1")).getValue();
  }
}
