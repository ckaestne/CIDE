package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

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
