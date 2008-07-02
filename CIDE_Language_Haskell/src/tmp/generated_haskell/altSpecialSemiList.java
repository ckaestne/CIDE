package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class altSpecialSemiList extends GenASTNode {
  public altSpecialSemiList(ArrayList<altSpecial> altSpecial, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<altSpecial>("altSpecial", altSpecial)
    }, firstToken, lastToken);
  }
  public altSpecialSemiList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new altSpecialSemiList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<altSpecial> getAltSpecial() {
    return ((PropertyList<altSpecial>)getProperty("altSpecial")).getValue();
  }
}
