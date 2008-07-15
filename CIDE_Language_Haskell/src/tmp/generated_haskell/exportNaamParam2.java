package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exportNaamParam2 extends exportNaamParam {
  public exportNaamParam2(naamOrVarList naamOrVarList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<naamOrVarList>("naamOrVarList", naamOrVarList)
    }, firstToken, lastToken);
  }
  public exportNaamParam2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exportNaamParam2(cloneProperties(),firstToken,lastToken);
  }
  public naamOrVarList getNaamOrVarList() {
    return ((PropertyZeroOrOne<naamOrVarList>)getProperty("naamOrVarList")).getValue();
  }
}
