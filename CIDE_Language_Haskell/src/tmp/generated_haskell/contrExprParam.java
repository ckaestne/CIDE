package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class contrExprParam extends GenASTNode {
  public contrExprParam(ArrayList<contrExprAssign> contrExprAssign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<contrExprAssign>("contrExprAssign", contrExprAssign)
    }, firstToken, lastToken);
  }
  public contrExprParam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new contrExprParam(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<contrExprAssign> getContrExprAssign() {
    return ((PropertyList<contrExprAssign>)getProperty("contrExprAssign")).getValue();
  }
}
