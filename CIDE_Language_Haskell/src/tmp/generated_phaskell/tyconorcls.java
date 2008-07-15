package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class tyconorcls extends GenASTNode {
  public tyconorcls(conid conid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conid>("conid", conid)
    }, firstToken, lastToken);
  }
  public tyconorcls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new tyconorcls(cloneProperties(),firstToken,lastToken);
  }
  public conid getConid() {
    return ((PropertyOne<conid>)getProperty("conid")).getValue();
  }
}
