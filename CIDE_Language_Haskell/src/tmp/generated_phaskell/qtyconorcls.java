package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qtyconorcls extends GenASTNode {
  public qtyconorcls(qconid qconid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qconid>("qconid", qconid)
    }, firstToken, lastToken);
  }
  public qtyconorcls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qtyconorcls(cloneProperties(),firstToken,lastToken);
  }
  public qconid getQconid() {
    return ((PropertyOne<qconid>)getProperty("qconid")).getValue();
  }
}
