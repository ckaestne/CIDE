package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class defaultdecl extends topdecl {
  public defaultdecl(list list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<list>("list", list)
    }, firstToken, lastToken);
  }
  public defaultdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new defaultdecl(cloneProperties(),firstToken,lastToken);
  }
  public list getList() {
    return ((PropertyOne<list>)getProperty("list")).getValue();
  }
}
