package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class imp2 extends imp {
  public imp2(tyconorcls tyconorcls, list list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<tyconorcls>("tyconorcls", tyconorcls),
      new PropertyZeroOrOne<list>("list", list)
    }, firstToken, lastToken);
  }
  public imp2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new imp2(cloneProperties(),firstToken,lastToken);
  }
  public tyconorcls getTyconorcls() {
    return ((PropertyOne<tyconorcls>)getProperty("tyconorcls")).getValue();
  }
  public list getList() {
    return ((PropertyZeroOrOne<list>)getProperty("list")).getValue();
  }
}
