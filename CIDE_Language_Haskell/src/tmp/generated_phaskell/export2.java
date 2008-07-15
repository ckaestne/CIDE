package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class export2 extends export {
  public export2(qtyconorcls qtyconorcls, details details, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qtyconorcls>("qtyconorcls", qtyconorcls),
      new PropertyZeroOrOne<details>("details", details)
    }, firstToken, lastToken);
  }
  public export2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new export2(cloneProperties(),firstToken,lastToken);
  }
  public qtyconorcls getQtyconorcls() {
    return ((PropertyOne<qtyconorcls>)getProperty("qtyconorcls")).getValue();
  }
  public details getDetails() {
    return ((PropertyZeroOrOne<details>)getProperty("details")).getValue();
  }
}
