package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class cdecls extends GenASTNode {
  public cdecls(cdeclsI cdeclsI, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<cdeclsI>("cdeclsI", cdeclsI)
    }, firstToken, lastToken);
  }
  public cdecls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cdecls(cloneProperties(),firstToken,lastToken);
  }
  public cdeclsI getCdeclsI() {
    return ((PropertyZeroOrOne<cdeclsI>)getProperty("cdeclsI")).getValue();
  }
}
