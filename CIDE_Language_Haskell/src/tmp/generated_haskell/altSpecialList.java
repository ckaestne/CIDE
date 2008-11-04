package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class altSpecialList extends GenASTNode {
  public altSpecialList(ArrayList<altSpecial> altSpecial, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<altSpecial>("altSpecial", altSpecial)
    }, firstToken, lastToken);
  }
  public altSpecialList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new altSpecialList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<altSpecial> getAltSpecial() {
    return ((PropertyList<altSpecial>)getProperty("altSpecial")).getValue();
  }
}
