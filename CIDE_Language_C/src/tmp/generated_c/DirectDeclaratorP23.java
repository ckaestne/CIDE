package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectDeclaratorP23 extends DirectDeclaratorP2 {
  public DirectDeclaratorP23(IdentifierList identifierList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<IdentifierList>("identifierList", identifierList)
    }, firstToken, lastToken);
  }
  public DirectDeclaratorP23(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectDeclaratorP23(cloneProperties(),firstToken,lastToken);
  }
  public IdentifierList getIdentifierList() {
    return ((PropertyZeroOrOne<IdentifierList>)getProperty("identifierList")).getValue();
  }
}
