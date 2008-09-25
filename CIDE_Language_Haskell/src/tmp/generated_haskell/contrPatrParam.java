package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class contrPatrParam extends GenASTNode {
  public contrPatrParam(ArrayList<contrPatrAssign> contrPatrAssign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<contrPatrAssign>("contrPatrAssign", contrPatrAssign)
    }, firstToken, lastToken);
  }
  public contrPatrParam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new contrPatrParam(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<contrPatrAssign> getContrPatrAssign() {
    return ((PropertyList<contrPatrAssign>)getProperty("contrPatrAssign")).getValue();
  }
}
